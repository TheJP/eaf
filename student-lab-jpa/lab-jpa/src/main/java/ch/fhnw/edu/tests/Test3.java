package ch.fhnw.edu.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.fhnw.edu.model.Customer;

public class Test3 {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"datasource-jpa.xml", 
		});

		EntityManagerFactory emf = context.getBean("entityManagerFactory", EntityManagerFactory.class);

		TestUtil.resetData(emf);
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Customer c = em.find(Customer.class, 1);
		c.setAge(55);
		c.getAddress().setStreet("Bahnhofstrasse 5");
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1_000_000; i++) {
			c.getAddress().getStreet();
		}
		System.out.println(System.currentTimeMillis() - start);
		
		em.getTransaction().commit();
		em.close();
		context.close();
		System.out.println("done");
	}
}
