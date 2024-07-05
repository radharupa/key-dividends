package com.keydividend;

import com.keydividend.config.MongoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Import(value= {MongoConfiguration.class})

@SpringBootApplication
public class KeyDividendsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeyDividendsApplication.class, args);
		
		
		//set variables in context
		//tickerSymbols1
		
		
	}
	
	// daily once job to check any new ticker symbols added




	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/v1/user/*").allowedOrigins("*");
				registry.addMapping("/v1/dividend/*").allowedOrigins("*");
				registry.addMapping("/v1/stock/*").allowedOrigins("*");
				registry.addMapping("/v1/stockNews/*").allowedOrigins("*");
				registry.addMapping("/v1/watchlist/*").allowedOrigins("*");
			}
		};
	}




}
