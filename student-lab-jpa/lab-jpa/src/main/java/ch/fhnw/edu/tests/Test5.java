package ch.fhnw.edu.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.fhnw.edu.model.Address;
import ch.fhnw.edu.model.Customer;


public class Test5 {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"datasource-jpa.xml", 
//				"db-manager.xml"
		});

		EntityManagerFactory emf = context.getBean("entityManagerFactory", EntityManagerFactory.class);

		TestUtil.deleteData(emf);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Customer c = new Customer("Gosling", 55);
		Address a = new Address("Infinite Loop 1", "Cupertino");
		c.setAddress(a);
		
		em.persist(a);
		em.persist(c);
		
		em.getTransaction().commit();
		em.close();
		context.close();
		System.out.println("done");
	}
}
