package ch.fhnw.edu.bank;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteAccountService extends Remote {
	void insertAccount(Account acc) throws RemoteException;
	List<Account> getAccounts(String name) throws RemoteException;
}
