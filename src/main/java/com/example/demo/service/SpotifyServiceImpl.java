package com.example.demo.service;

import com.example.demo.DTOs.SpotifyResponseDTO;
import com.example.demo.service.interfaces.SpotifyService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerErrorException;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Base64;

@Service
public class SpotifyServiceImpl implements SpotifyService {
    @Value("${spotify.clientId}")
    private String clientId;

    @Value("${spotify.clientSecret}")
    private String clientSecret;

    @Value("${spotify.token.url}")
    private String spotifyTokenUrl;

    @Value("${spotify.api.url}")
    private String spotifyApiUrl;

    @Value("${spotify.featured.playlists.url}")
    private String featuredPlaylists;

    @Autowired
   private WebClient webClient;

    @Override
    public Mono<SpotifyResponseDTO> fetchFeaturedPlaylistData() {
        return getAccessToken()
                .flatMap(this::getPlaylist);
    }

    private Mono<String> getAccessToken() {
        return webClient.post()
                .uri(spotifyTokenUrl)
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new HttpClientErrorException(response.statusCode())))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new HttpServerErrorException(response.statusCode())))
                .bodyToMono(String.class)
                .flatMap(this::parseAccessToken);
    }

    private Mono<String> parseAccessToken(String accessTokenResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(accessTokenResponse);
            String accessToken = node.get("access_token").asText();
            return Mono.just(accessToken);
        } catch (IOException e) {
            return Mono.error(new ServerErrorException("Error parsing access token response", e));
        }
    }

    private Mono<SpotifyResponseDTO> getPlaylist(String accessToken) {
        return webClient.get()
                .uri(spotifyApiUrl + featuredPlaylists)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(String.class)
                .map(responseString -> {
                    SpotifyResponseDTO dto = new SpotifyResponseDTO();
                    dto.setResponse(responseString);
                    return dto;
                });
    }
}
