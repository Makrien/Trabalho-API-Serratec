package br.gov.serratec.grupo05api.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ConsumoApi {

	private static String buscaDados(String url) {
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url)).build();

		HttpResponse<String> response = null;

		try {
			response = client.send(request, BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return response.body();
	}
	
	
	public static String obterDados(String cep) {
		String url = "https://viacep.com.br/ws/" 
				+ cep 
				+ "/json/";

		return buscaDados(url);
	}
}
