package ch.fhnw.edu.tests.assignment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.fhnw.edu.model.Customer;
import ch.fhnw.edu.model.Order;
import ch.fhnw.edu.tests.TestUtil;


public class TestA2 {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"datasource-jpa.xml", 
//				"db-manager.xml"
		});

		EntityManagerFactory emf = context.getBean("entityManagerFactory", EntityManagerFactory.class);
		TestUtil.deleteData(emf);

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Customer c1 = new Customer("Haller", 52);
		Customer c2 = new Customer("Schneider", 66);

		Order o1 = new Order();
		Order o2 = new Order();
		Order o3 = new Order();

		c1.addOrder(o1);
		c1.addOrder(o2);

		c2.addOrder(o1);
		c2.addOrder(o3);

		em.persist(c1);
		em.persist(c2);

		em.getTransaction().commit();
		em.close();
		context.close();
		System.out.println("done");
	}

}
