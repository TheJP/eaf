package ch.fhnw.edu.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Query;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.fhnw.edu.model.Customer;

public class Test7 {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"datasource-jpa.xml", 
		});

		EntityManagerFactory emf = context.getBean("entityManagerFactory", EntityManagerFactory.class);

		TestUtil.resetData(emf);
		
		EntityManager em = emf.createEntityManager();
		System.out.println("Default> " + em.getFlushMode());
		em.setFlushMode(FlushModeType.COMMIT);
		System.out.println("Current> " + em.getFlushMode());
		
		em.getTransaction().begin();
		
		Customer c = em.find(Customer.class, 1);
		c.getAddress().setCity("Basel");

//		em.flush();
		
		Query q = em.createQuery("select a.city from Address a");
		List<?> cities = q.getResultList();
		for (Object city : cities) {
			System.out.println(city);
		}
		
		em.getTransaction().commit();
		em.close();
		context.close();
		System.out.println("done");
	}
}
