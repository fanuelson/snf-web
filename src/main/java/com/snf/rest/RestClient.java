package com.snf.rest;

import java.io.Serializable;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;

import com.snf.model.Servico;
import com.snf.util.DataUtil;
import com.snf.vo.FiltroConsultaServicoVO;

public class RestClient implements Serializable {
	
	private static final long serialVersionUID = 1L;

	static final String URL_BASE = "http://localhost:8080";
	static final String REST_APP = "/snf-rest";
	static final String REST_PATTERN = "/rest";
	static final String APPLICATION_JSON = "application/json";
	static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	
	private static String montarUrlResource(String serviceResource){
		return String.format("%s%s%s%s", URL_BASE, REST_APP, REST_PATTERN, serviceResource);
	}
	
	public static <T> T httpGetJson(String serviceResource, Class<T> returnType){
		Client client = ClientBuilder.newClient();
		WebTarget webResource = client.target(montarUrlResource(serviceResource));
		String responseJson = webResource.request(APPLICATION_JSON).get(String.class);
		T tp = null;
		try{
			ObjectMapper om = new ObjectMapper();
			tp = om.readValue(responseJson, returnType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return tp;
	}
	
	
	
	public static void main(String[] args) {
		try {
			//imprimeServicoFirst();
			//imprimeSomaTotalServicos();
			imprimeTipoUsuario();
			//imprimirTransacoes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void imprimirTransacoes() {
		try{
//			List<Caixa> caixas = (List<Caixa>) httpGetJsonAsCollection("/caixas/fetchTransacoes", Caixa.class);
//			System.out.println(caixas.get(0).getTransacoes().get(0).getData());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public static void imprimeSomaTotalServicos(){
		try {
			FiltroConsultaServicoVO filtro = new FiltroConsultaServicoVO();
			filtro.setDataInicial(DataUtil.diminuirDias(new Date(), 100));
			filtro.setDataFinal(DataUtil.somarDias(new Date(), 100));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//RESOLVER PROBLEMA DA LISTA
	public static void imprimeTipoUsuario(){
		try {
			//List<TipoUsuario> tp = (List<TipoUsuario>) httpGetJsonAsCollection("/tiposUsuario",TipoUsuario.class);
			//TipoUsuario tp = getJson("/tipoUsuario/first", TipoUsuario.class);
			//System.out.println( ((TipoUsuario)tp.get(0)).getPermissao());
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
