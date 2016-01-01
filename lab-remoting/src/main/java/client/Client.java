package client;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.fhnw.edu.bank.Account;
import ch.fhnw.edu.bank.AccountService;

public class Client {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("client/context.xml");
		
		Object obj;
		obj = context.getBean("accountService");
		
		System.out.println("obj.getClass() = " + obj.getClass().getCanonicalName());
		
		System.out.println("implemented interfaces:");
		for (Class<?> c : obj.getClass().getInterfaces()) {
			System.out.println("> " + c.getCanonicalName());
		}
		
		if (obj instanceof AccountService) {
			AccountService service = (AccountService) obj;
			service.insertAccount(new Account("Mueller"));
			List<Account> accounts = service.getAccounts("Mueller");
			System.out.println(accounts.size() + " accounts returned");
		}
		
		context.close();
	}

}
