package com.example.demo;

import com.example.demo.model.Album;
import com.example.demo.model.Artist;
import com.example.demo.model.Song;
import com.example.demo.repository.ArtistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner initData(ArtistRepository repository) {
//		return (args) -> {
//			Artist artist = new Artist();
//			artist.setName("Artist1");
//
//			repository.save(artist);
//		};
//	}
}
