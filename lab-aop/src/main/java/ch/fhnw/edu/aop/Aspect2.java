package ch.fhnw.edu.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

@Component
//@Aspect
public class Aspect2 {

	private Log log = LogFactory.getLog(this.getClass());

	/**
	* After Advice: limits join points to calls into the dataaccess
	* layer and methods with just one parameter of type Long
	*/
	@After("within(ch.fhnw.edu.dataaccess..*) && args(arg)")
	public void showRequest(JoinPoint jp, Long arg){
		log.info(String.format("After %s with arg: %d", jp.getSignature(), arg));
	}

	/**
	* AfterReturning Advice: limits join points to calls into the dataaccess
	* layer and methods with just one parameter of type Long
	*/
	@AfterReturning("within(ch.fhnw.edu.dataaccess..*) && args(arg)")
	public void showResponse(JoinPoint jp, Long arg){
		log.info(String.format("AfterReturning %s with arg: %d", jp.getSignature(), arg));
	}

	/**
	* AfterThrowing Advice: limits join points to calls into the dataaccess
	* layer and methods with just one parameter of type Object
	*/
	@AfterThrowing("within(ch.fhnw.edu.dataaccess..*) && args(arg)")
	public void showException(JoinPoint jp, Object arg){
		log.info(String.format("AfterThrowing %s with arg: %s", jp.getSignature(), arg));
	}

	/**
	* Around Advice: Limits join points to calls into the service layer and
	* methods with just one parameter of type Long. If input parameter is null
	* throw an IllegalArgumentException otherwise proceed as usual.
	*/
	@Around("within(ch.fhnw.edu.domain.service..*) && args(id)")
	public Object validate(ProceedingJoinPoint pjp, Long id) throws Throwable {
		if(id != null){
			log.info(String.format("+ Around before: %s", pjp.getSignature()));
			Object o = pjp.proceed();
			log.info(String.format("- Around after: %s", pjp.getSignature()));
			return o;
		} else {
			log.info("Error: id was null");
			throw new IllegalArgumentException("Id was null");
		}
	}
}
