package ch.fhnw.edu.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.fhnw.edu.model.Movie;
import ch.fhnw.edu.model.User;

public class Test1 {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"datasource-jpa.xml", 
//				"db-manager.xml"
		});

		EntityManagerFactory emf = context.getBean("entityManagerFactory", EntityManagerFactory.class);
		TestUtil.resetData(emf);

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, 1L);
		System.out.println(movie.getTitle());

		User u = em.createQuery("SELECT u FROM Rental r join r.user u WHERE r.movie = :movie", User.class)
			.setParameter("movie", movie)
			.getSingleResult();
		System.out.println(u.getFirstName() + " " + u.getLastName());
		
		em.getTransaction().commit();
		em.close();
		context.close();
		System.out.println("done");
	}
	
}
