package client;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import org.springframework.remoting.rmi.RmiInvocationHandler;
import org.springframework.remoting.support.RemoteInvocation;

import ch.fhnw.edu.bank.Account;

public class Client2 {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Object obj = Naming.lookup("rmi://localhost:1099/AccountService");
		System.out.println(obj.getClass());
		RmiInvocationHandler handler = (RmiInvocationHandler) obj;
		//1.
		RemoteInvocation invocation = new RemoteInvocation();
		invocation.setMethodName("insertAccount");
		invocation.setParameterTypes(new Class<?>[]{ Account.class });
		invocation.setArguments(new Object[]{ new Account("XXX") });
		handler.invoke(invocation);
		//2.
		RemoteInvocation invocation2 = new RemoteInvocation();
		invocation2.setMethodName("getAccounts");
		invocation2.setParameterTypes(new Class<?>[]{ String.class });
		invocation2.setArguments(new Object[]{ "Mueller" });
		Object result = handler.invoke(invocation2);
		@SuppressWarnings("unchecked")
		List<Account> list = (List<Account>)result;
		System.out.println("Count mueller: " + list.size());
	}
}
