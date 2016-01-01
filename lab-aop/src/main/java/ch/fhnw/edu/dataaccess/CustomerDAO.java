package ch.fhnw.edu.dataaccess;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.edu.domain.model.Customer;

public interface CustomerDAO {
	Customer getById(Long id);

	List<Customer> getByName(String name);

	List<Customer> getAll();
	@Transactional(propagation = Propagation.MANDATORY)
	Customer saveOrUpdate(Customer c);
	@Transactional(propagation = Propagation.MANDATORY)
	void delete(Customer c);

	Customer manage(Customer c);
}
