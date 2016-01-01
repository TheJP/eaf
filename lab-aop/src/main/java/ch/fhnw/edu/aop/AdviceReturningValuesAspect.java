package ch.fhnw.edu.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import ch.fhnw.edu.domain.model.Customer;

@Component
@Aspect
public class AdviceReturningValuesAspect {

	private Log log = LogFactory.getLog(this.getClass());

	/**
	* @AfterReturning Advice: limits join points to calls to methods
	* returning an instance of type Customer. Update property "lastname"
	* to "GUGUS" to satisfy the test.
	* Writes value of Customer into log.
	*/
	@AfterReturning(
		pointcut = "execution(ch.fhnw.edu.domain.model.Customer *(..))",
		returning = "customer"
	)
	public void advice1(JoinPoint jp, Customer customer){
		log.info(String.format("AfterReturning %s Changed lastname %s to GUGUS", jp.getSignature(), customer.getLastName()));
		customer.setLastName("GUGUS");
	}

	/**
	* AfterThrowing Advice: limits join points to calls into the dataaccess
	* layer and methods with just one parameter of type Object.
	* Writes exception into the log.
	*/
	@AfterThrowing(
		pointcut = "within(ch.fhnw.edu.dataaccess..*) && args(obj)",
		throwing = "e"
	)
	public void advice2(JoinPoint jp, Object obj, Throwable e){
		log.info(String.format("AfterThrowing %s arg: %s", jp.getSignature(), obj), e);
	}
}
