package com.pranjal.intuit.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class Validation {
	public static final Logger LOGGER=LoggerFactory.getLogger(Validation.class);
	
	
	@Around("execution (* com.pranjal.intuit.companyms.CompanyServiceImpl.getCompanyById(..)) && args(postId)")
	public Object validateAndUpdate(ProceedingJoinPoint jp,int postId) throws Throwable {
	if (postId<0) {
		LOGGER.info("CompanyId is negative, updating it");
		
		postId=-postId;
		LOGGER.info("new Value "+postId);
	}
	
	Object obj=jp.proceed(new Object[] {postId});
		
		
		
		return obj;
	}
}
