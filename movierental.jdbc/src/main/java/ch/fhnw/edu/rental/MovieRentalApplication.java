package ch.fhnw.edu.rental;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.fhnw.edu.rental.gui.BusinessLogic;
import ch.fhnw.edu.rental.gui.BusinessLogicLocal;
import ch.fhnw.edu.rental.gui.MovieRentalApplicationGui;

public class MovieRentalApplication {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"application.xml",
				"datasource-memory.xml"
//				"datasource-jdbc.xml"
		});

		final BusinessLogic service = new BusinessLogicLocal(context);
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MovieRentalApplicationGui(service).setVisible(true);
			}
		});
	}

}
