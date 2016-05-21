package com.snf.rest;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.snf.util.JsonUtils;

public class RestClient implements Serializable {

	private static final long serialVersionUID = 1L;

	static final String URL_BASE = "http://localhost:8080";
	static final String REST_APP = "/snf-rest";
	static final String REST_PATTERN = "/rest";
	static final String APPLICATION_JSON = "application/json";
	static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	private static String montarUrlResource(String serviceResource) {
		return String.format("%s%s%s%s", URL_BASE, REST_APP, REST_PATTERN, serviceResource);
	}

	public static <T> T httpGetJson(String serviceResource, Class<T> returnType) {
		Client client = ClientBuilder.newClient();
		WebTarget webResource = client.target(montarUrlResource(serviceResource));
		String responseJson = webResource.request(APPLICATION_JSON).get(String.class);
		T retorno = null;
		try {
			retorno = JsonUtils.fromJson(responseJson, returnType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public static <T> List<T> httpGetJsonCollection(String serviceResource, Class<T> returnType) {
		Client client = ClientBuilder.newClient();
		WebTarget webResource = client.target(montarUrlResource(serviceResource));
		String responseJson = webResource.request(APPLICATION_JSON).get(String.class);
		List<T> tp = null;
		try {
			tp = JsonUtils.getCollectionfromJson(responseJson, returnType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tp;
	}

	public static <T> List<T> httpPostJsonCollection(String serviceResource, Class<T> returnType, Object param) {
		Client client = ClientBuilder.newClient();
		WebTarget webResource = client.target(montarUrlResource(serviceResource));
		Response response = webResource.request(APPLICATION_JSON).accept(APPLICATION_JSON).post(Entity.json(param));
		List<T> registros = null;
		try {
			registros = JsonUtils.getCollectionfromJson(response.readEntity(String.class), returnType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registros;
	}

	public static <T> T httpPostJson(String serviceResource, Class<T> returnType, Object param) {
		Client client = ClientBuilder.newClient();
		WebTarget webResource = client.target(montarUrlResource(serviceResource));
		Response response = webResource.request(APPLICATION_JSON).accept(APPLICATION_JSON).post(Entity.json(param));
		T retorno = null;
		try {
			retorno = JsonUtils.fromJson(response.readEntity(String.class), returnType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

}
