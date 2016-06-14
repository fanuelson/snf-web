package com.snf.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import com.snf.annotation.LogApp;

@LogApp
@Interceptor
public class LogInterceptor {

	@AroundInvoke
	public Object logError(InvocationContext ctx) {
		Logger log = Logger.getLogger(ctx.getTarget().getClass());
		Object retorno = null ;
		try{
			retorno = ctx.proceed();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retorno;
	}
}
