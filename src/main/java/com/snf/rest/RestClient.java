package com.snf.rest;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;

import com.snf.exception.RestException;
import com.snf.util.JsonUtils;

public class RestClient implements Serializable {

	private static final long serialVersionUID = 1L;

	static final String URL_BASE = "http://localhost:8080";
	static final String REST_APP = "/snf-rest";
	static final String REST_PATTERN = "/rest";
	static final String APPLICATION_JSON = "application/json";
	static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	
	private RestClient() { }

	private static String montarUrlResource(String serviceResource) {
		return String.format("%s%s%s%s", URL_BASE, REST_APP, REST_PATTERN, serviceResource);
	}
	
	private static WebTarget getWebTarget(String serviceResource) {
		Client client = ClientBuilder.newClient();
		return client.target(montarUrlResource(serviceResource));
	}

	public static <T> T httpGetJson(String serviceResource, Class<T> returnType) throws RuntimeException {
		WebTarget wt = getWebTarget(serviceResource);
		Response response = wt.request(APPLICATION_JSON).get();
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(returnType);
		}else{
			RestException re = response.readEntity(RestException.class);
			throw new RuntimeException(re.getMessage());
		}
	}

	public static <T> List<T> httpGetJsonCollection(String serviceResource, Class<T> returnType) {
		WebTarget webResource = getWebTarget(serviceResource);
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

	public static <T> T httpPostJson(String serviceResource, Object param) throws RuntimeException {
		return httpPostJson(serviceResource, null, param);
	}
	
	public static <T> T httpPostJson(String serviceResource, Class<T> returnType, Object param) throws RuntimeException {
		WebTarget webResource = getWebTarget(serviceResource);
		String om = "";
		try{
			om = new ObjectMapper().writeValueAsString(param);
		}catch(Exception e){
			e.printStackTrace();
		}
		Response response = webResource.request(APPLICATION_JSON).accept(APPLICATION_JSON).post(Entity.json(om));
		if(response.getStatus() == Status.OK.getStatusCode()) {
			if(response.hasEntity())
				return response.readEntity(returnType);
			else
				return null;
		}else{
			RestException re = response.readEntity(RestException.class);
			throw new RuntimeException(re.getMessage());
		}
	}

}
