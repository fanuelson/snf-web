package com.snf.interceptor;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import com.snf.annotation.LogApp;

@LogApp
@Interceptor
public class LogInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@AroundInvoke
	public Object logError(InvocationContext ctx) throws Exception {
		Logger log = Logger.getLogger(ctx.getTarget().getClass());
		try{
			return ctx.proceed();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
}
