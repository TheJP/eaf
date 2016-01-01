package ch.fhnw.edu.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.edu.dataaccess.CustomerDAO;
import ch.fhnw.edu.domain.model.Customer;

@Service
@Transactional
public class CustomerService {
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CustomerDAO customerDAO;
	
	public Customer getById(Long id) {
		return customerDAO.getById(id);
	}
	
	public List<Customer> findByName(String name) {
		return customerDAO.getByName(name);
	}
	
	public List<Customer> findAllCustomers() {
		return customerDAO.getAll();
	}
	
	public Customer saveOrUpdateCustomer(Customer customer) throws CustomerServiceException {
		if (customer == null) {
			throw new CustomerServiceException("parameter is not set!");
		}
		try {
			customer = customerDAO.saveOrUpdate(customer);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomerServiceException(e.getMessage());
		}
		if (log.isDebugEnabled()) {
			log.debug("saved or updated customer[" + customer.getId() + "]");
		}
		return customer;
	}

	public void deleteCustomer(Customer customer) throws CustomerServiceException {
		if (customer == null) {
			throw new CustomerServiceException("'customer' parameter is not set!");
		}
		
		if (customer.getId() != null) {
			customer = customerDAO.manage(customer);
		}

		customerDAO.delete(customer);
		
		if (log.isDebugEnabled()) {
			log.debug("customer[" + customer.getId() + "] deleted");
		}
	}	
}
