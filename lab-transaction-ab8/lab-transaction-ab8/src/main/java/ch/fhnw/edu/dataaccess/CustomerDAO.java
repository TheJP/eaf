package ch.fhnw.edu.dataaccess;

import java.util.List;

import ch.fhnw.edu.domain.model.Customer;

public interface CustomerDAO {
	Customer getById(Long id);

	List<Customer> getByName(String name);

	List<Customer> getAll();
	
	Customer saveOrUpdate(Customer c);

	void delete(Customer c);

	Customer manage(Customer c);
}
