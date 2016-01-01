package server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		new ClassPathXmlApplicationContext("server/context.xml");
		System.out.println("server started");
	}

}
