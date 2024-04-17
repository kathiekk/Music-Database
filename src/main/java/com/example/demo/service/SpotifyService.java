package com.example.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerErrorException;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Base64;

@Service
public class SpotifyService {
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

   private  final WebClient webClient = WebClient.create();

    public Mono<String> getFeaturedPlaylists() {
        return webClient.post()
                .uri(spotifyTokenUrl)
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(accessTokenResponse -> {
                    String accessToken;

                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode node = objectMapper.readTree(accessTokenResponse);
                        accessToken = node.get("access_token").asText();
                    } catch (IOException e) {
                        return Mono.error(new ServerErrorException("Error parsing access token response", e));
                    }

                    return webClient.get()
                            .uri(spotifyApiUrl+featuredPlaylists)
                            .header("Authorization", "Bearer " + accessToken)
                            .retrieve()
                            .bodyToMono(String.class);
                });
    }
}
