package com.pranjal.intuit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logging {

	public static final Logger LOGGER=LoggerFactory.getLogger(Logging.class);
	
	@Before("execution (* com.pranjal.intuit.companyms.CompanyServiceImpl.getCompanyById(..))")
	public void logMethodCall(JoinPoint jp) {
		LOGGER.info("Method Called "+jp.getSignature().getName());
	}
	
	
	
	@After("execution (* com.pranjal.intuit.companyms.CompanyServiceImpl.getCompanyById(..))")
	public void logMethodExecuted(JoinPoint jp) {
		LOGGER.info("Reached end of method "+jp.getSignature().getName());
	}
	
	
	@AfterThrowing("execution (* com.pranjal.intuit.companyms.CompanyServiceImpl.*(..)) ")
	public void logMethodCrashed(JoinPoint jp) {
		LOGGER.info("Method crashed "+jp.getSignature().getName());
	}
	
	
	
	@AfterReturning("execution (* com.pranjal.intuit.companyms.CompanyServiceImpl.*(..))")
	public void logMethodExecutedSuccess(JoinPoint jp) {
		LOGGER.info("Method Executed Successfully "+jp.getSignature().getName());
	}
}
