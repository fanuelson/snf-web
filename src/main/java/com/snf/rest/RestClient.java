package com.snf.rest;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.snf.model.TipoUsuario;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestClient {
	
	static final String URL_BASE = "http://localhost:8080";
	static final String REST_APP = "/snf-rest";
	static final String REST_PATTERN = "/rest";
	static final String APPLICATION_JSON = "application/json";
	
	private static String montarUrlResource(String serviceResource){
		return String.format("%s%s%s%s", URL_BASE, REST_APP, REST_PATTERN, serviceResource);
	}
	
	public static <T> T getJson(String serviceResource, Class<T> returnType){
		Client client = Client.create();
		WebResource webResource = client.resource(montarUrlResource(serviceResource));
		ClientResponse response = webResource.accept(APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		String responseJson = response.getEntity(String.class);
		T tp = new GsonBuilder().create().fromJson(responseJson, new TypeToken<T>(){}.getType());
		return tp;
	}
	
	public static void main(String[] args) {
		try {
			List<TipoUsuario> tp = getJson("/tipoUsuario", new ArrayList<TipoUsuario>().getClass());
			
			//TipoUsuario tp = getJson("/tipoUsuario/first", TipoUsuario.class);
			System.out.println(tp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
