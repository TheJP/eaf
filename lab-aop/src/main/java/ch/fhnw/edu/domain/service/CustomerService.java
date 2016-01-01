package ch.fhnw.edu.domain.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.edu.dataaccess.CustomerDAO;
import ch.fhnw.edu.domain.model.Customer;

@Service
@Transactional
public class CustomerService {
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Customer getById(Long id) {
		return customerDAO.getById(id);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Customer> findByName(String name) {
		return customerDAO.getByName(name);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Customer> findAllCustomers() {
		return customerDAO.getAll();
	}
	
	@Transactional(rollbackFor = CustomerServiceException.class)
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

	@Transactional
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
