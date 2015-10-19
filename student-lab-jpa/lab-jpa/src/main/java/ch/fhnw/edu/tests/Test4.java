package ch.fhnw.edu.tests;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test4 {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"datasource-jpa.xml", 
				"db-manager.xml"
		});

		EntityManagerFactory emf = context.getBean("entityManagerFactory", EntityManagerFactory.class);

		TestUtil.resetData(emf);
		context.close();
		System.out.println("done");
	}
}
