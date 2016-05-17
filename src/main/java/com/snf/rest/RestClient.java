package com.snf.rest;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.snf.model.Caixa;
import com.snf.model.Servico;
import com.snf.model.TipoUsuario;
import com.snf.util.DataUtil;
import com.snf.util.JsonUtils;
import com.snf.vo.FiltroConsultaServicoVO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestClient {
	
	static final String URL_BASE = "http://localhost:8080";
	static final String REST_APP = "/snf-rest";
	static final String REST_PATTERN = "/rest";
	static final String APPLICATION_JSON = "application/json";
	static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	
	private static String montarUrlResource(String serviceResource){
		return String.format("%s%s%s%s", URL_BASE, REST_APP, REST_PATTERN, serviceResource);
	}
	
	public static <T> T httpGetJson(String serviceResource, Class<T> returnType){
		Client client = Client.create();
		WebResource webResource = client.resource(montarUrlResource(serviceResource));
		ClientResponse response = webResource.accept(APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		String responseJson = response.getEntity(String.class);
		T tp = null;
		try{
			tp = (T) JsonUtils.fromJson(responseJson, returnType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return tp;
	}
	
	public static <T> Collection<T> httpGetJsonAsCollection(String serviceResource, Class<T> classe){
		Client client = Client.create();
		WebResource webResource = client.resource(montarUrlResource(serviceResource));
		ClientResponse response = webResource.accept(APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		String responseJson = response.getEntity(String.class);
		Collection<T> as=null;
		try{
			as = JsonUtils.jsonToCollection(responseJson, classe);
		}catch(Exception e){
			e.printStackTrace();
		}
		return as;
	}
	
	public static <T> T httpPostJson(String serviceResource, Class<T> returnType, Object parametro){
		Client client = Client.create();
		WebResource webResource = client.resource(montarUrlResource(serviceResource));
		String parametroJson = new GsonBuilder().setDateFormat(JSON_DATE_FORMAT).create().toJson(parametro);
		ClientResponse response = webResource
				.accept(APPLICATION_JSON)
				.type(APPLICATION_JSON)
				.post(ClientResponse.class, parametroJson);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
		String responseJson = response.getEntity(String.class);
		T tp = null;
		try{
			tp = (T) JsonUtils.fromJson(responseJson, returnType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return tp;
	}
	
	public static <T> Collection<T> postJsonAsCollection(String serviceResource, Class<T> returnType, Object parametro){
		Client client = Client.create();
		WebResource webResource = client.resource(montarUrlResource(serviceResource));
		String parametroJson = new GsonBuilder().setDateFormat(JSON_DATE_FORMAT).create().toJson(parametro);
		ClientResponse response = webResource
				.accept(APPLICATION_JSON)
				.type(APPLICATION_JSON)
				.post(ClientResponse.class, parametroJson);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
		
		String responseJson = response.getEntity(String.class);
		Collection<T> tp = null;
		try{
			ObjectMapper om =  new ObjectMapper(); 
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			tp = (Collection<T>) om.readValue(responseJson, om.getTypeFactory().constructCollectionType(Collection.class, returnType));
		}catch(Exception e){
			e.printStackTrace();
		}
		return tp;
	}
	
	public static void main(String[] args) {
		try {
			imprimeServicoFirst();
			imprimeSomaTotalServicos();
			imprimeTipoUsuario();
			imprimirTransacoes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void imprimirTransacoes() {
		try{
			List<Caixa> caixas = (List<Caixa>) httpGetJsonAsCollection("/caixas/fetchTransacoes", Caixa.class);
			System.out.println(caixas.get(0).getTransacoes().get(0).getData());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public static void imprimeSomaTotalServicos(){
		try {
			FiltroConsultaServicoVO filtro = new FiltroConsultaServicoVO();
			filtro.setDataInicial(DataUtil.diminuirDias(new Date(), 100));
			filtro.setDataFinal(DataUtil.somarDias(new Date(), 100));
			Double d = httpPostJson("/servicos/somaTotal", Double.class, filtro);
			System.out.println(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//RESOLVER PROBLEMA DA LISTA
	public static void imprimeTipoUsuario(){
		try {
			List<TipoUsuario> tp = (List<TipoUsuario>) httpGetJsonAsCollection("/tiposUsuario",TipoUsuario.class);
			//TipoUsuario tp = getJson("/tipoUsuario/first", TipoUsuario.class);
			System.out.println( ((TipoUsuario)tp.get(0)).getPermissao());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void imprimeServicoFirst(){
		try {
			Servico s = httpGetJson("/servicos/first", Servico.class);
			//TipoUsuario tp = getJson("/tipoUsuario/first", TipoUsuario.class);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
