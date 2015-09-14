package edu.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldApplicationContext {

	public static void main(String[] args){
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("helloConfig2.xml");
		MessageRenderer renderer = (MessageRenderer) context.getBean("renderer");
		renderer.render();
	}

}
