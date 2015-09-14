package edu.spring;

import java.io.IOException;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

@SuppressWarnings("deprecation")
public class DecoupledHelloWorldWithSpring {
	private static BeanFactory getBeanFactory() {
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("helloConfig.xml"));
		return factory;
	}

	public static void main(String[] args) {
		BeanFactory factory = getBeanFactory();
		MessageRenderer mr = (MessageRenderer) factory.getBean("renderer");
		mr.render();
	}
}
