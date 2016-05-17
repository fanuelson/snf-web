package com.snf.util;

import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	public static String toJson(Object obj) throws JsonProcessingException{
		try{
			ObjectMapper om = new ObjectMapper();
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return om.writeValueAsString(obj);
		}catch(JsonProcessingException ex){
			throw ex;
		}
	}
	
	public static <T> Collection<T> jsonToCollection(String json, Class<T> classe) throws Exception {
		try{
			ObjectMapper om = new ObjectMapper();
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return (Collection<T>) om.readValue(json, om.getTypeFactory().constructCollectionType(Collection.class, classe));
		}catch(Exception e) {
			throw e;
		}
	}
	
	public static <T> Object fromJson(String json, Class<T> clazz) throws Exception{
		try{
			ObjectMapper om = new ObjectMapper();
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return (T) om.readValue(json, clazz);
		}catch(Exception e){
			throw e;
		}
	}
	
	
}
