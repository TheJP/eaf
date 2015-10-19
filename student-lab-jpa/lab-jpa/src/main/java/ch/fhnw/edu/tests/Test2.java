package ch.fhnw.edu.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.fhnw.edu.model.Customer;

public class Test2 {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"datasource-jpa.xml", 
		});

		EntityManagerFactory emf = context.getBean("entityManagerFactory", EntityManagerFactory.class);

		TestUtil.resetData(emf);
		
		EntityManager em = emf.createEntityManager();
		
		Customer c1 = em.find(Customer.class, 1);
		Customer c2 = em.find(Customer.class, 1);

		System.out.println(c1);
		System.out.println(c2);

		System.out.println(c1 == c2);
		
		em.close();
		System.out.println("done");
		
		context.close();
	}
}
