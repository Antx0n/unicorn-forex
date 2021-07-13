package com.antxon.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ForexService {
	
	String baseUrl = "https://currency-exchange.p.rapidapi.com/exchange";
	String rapidapiHost = "currency-exchange.p.rapidapi.com";
	String rapidapiKey = "dc2289ef80msh97ad6a32f8984a5p1b4e97jsn2515c4d89ded";
	
		public ResponseEntity<String> getRate(String cur) {			
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
				.queryParam("from","KES")
				.queryParam("to",cur)
				.queryParam("q","1");
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(builder.toUriString()))
				.header("x-rapidapi-key", rapidapiKey)
				.header("x-rapidapi-host", "currency-exchange.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
		HttpResponse<String> response;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response.body(), new HttpHeaders(), HttpStatus.OK);
	}
	
}
