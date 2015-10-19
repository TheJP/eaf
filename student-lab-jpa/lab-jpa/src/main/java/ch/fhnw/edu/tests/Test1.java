package ch.fhnw.edu.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.fhnw.edu.model.Customer;

public class Test1 {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"datasource-jpa.xml", 
//				"db-manager.xml"
		});

		EntityManagerFactory emf = context.getBean("entityManagerFactory", EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Customer c;
		c = new Customer("Meier", 24);
		em.persist(c);
		System.out.println(c.getId());
		
		c = new Customer("Mueller", 24);
		em.persist(c);
		System.out.println(c.getId());

		em.getTransaction().commit();
		em.close();
		context.close();
		System.out.println("done");
	}
}
