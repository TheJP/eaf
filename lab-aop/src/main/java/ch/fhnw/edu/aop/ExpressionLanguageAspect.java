package ch.fhnw.edu.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class ExpressionLanguageAspect {
	private int counter = 0;
	private Log log = LogFactory.getLog(this.getClass());	
	
	// limits join points to calls to all methods within ch.fhnw.edu and its subpackages
//	@Before("execution(* ch.fhnw.edu..*(..))")

	// limits join points to calls to all methods returning a Customer instance
//	@Before("execution(ch.fhnw.edu.domain.model.Customer *(..))")

	// limits join points to calls into the dataaccess layer
//	@Before("execution(* ch.fhnw.edu.dataaccess..*(..))")
//  or
//	@Before("within(ch.fhnw.edu.dataaccess..*)")

	// limits join points to calls to CustomerService
//	@Before("execution(* *..CustomerService.*(..))")
//  or
//	@Before("this(ch.fhnw.edu.domain.service.CustomerService)")

	// limits join points to calls where the method argument is of type Customer
	@Before("execution(* *..*(.., ch.fhnw.edu.domain.model.Customer, ..))")
//  or
//	@Before("args(ch.fhnw.edu.domain.model.Customer)")
	public void showPointCut(JoinPoint jp) {
		log.info(jp.toLongString());
		counter++;
	}

	public void logCounter() {
		log.info("Hits = " + counter);
	}
}
