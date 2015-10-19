package ch.fhnw.edu.tests.assignment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.fhnw.edu.model.Address;
import ch.fhnw.edu.model.Customer;
import ch.fhnw.edu.tests.TestUtil;


public class TestA1 {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"datasource-jpa.xml", 
//				"db-manager.xml"
		});

		EntityManagerFactory emf = context.getBean("entityManagerFactory", EntityManagerFactory.class);
		TestUtil.deleteData(emf);

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Customer c1 = new Customer("Lee", 44);
		Customer c2 = new Customer("Gosling", 55);
		
		Address a = new Address("Infinite Loop 1", "Cupertino");

		c1.setAddress(a);
		c2.setAddress(a);
		
		em.persist(a);
		em.persist(c1);
		em.persist(c2);
		
		em.getTransaction().commit();
		em.close();
		context.close();
		System.out.println("done");
	}

}
