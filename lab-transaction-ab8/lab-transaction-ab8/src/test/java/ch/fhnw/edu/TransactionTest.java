package ch.fhnw.edu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.fhnw.edu.domain.model.Customer;
import ch.fhnw.edu.domain.service.CustomerService;
import ch.fhnw.edu.domain.service.CustomerServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfiguration.class})
// @DirtiesContext forces reloading the spring context after each test
// USE IT WITH CARE (slows down the test suite!)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class TransactionTest {
	@Autowired CustomerService service;
	
	@Test
	public void aufgabe1() throws CustomerServiceException {
		List<Customer> customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(4, customers.size());
		
		Customer c = new Customer("Hugo", "Test");
		service.saveOrUpdateCustomer(c);
		
		customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(5, customers.size());		
	}
	
	@Test
	public void aufgabe2() throws CustomerServiceException {
		List<Customer> customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(4, customers.size());
		
		Customer customer = customers.get(0);
		service.deleteCustomer(customer);
		
		customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(3, customers.size());		
	}
	
	@Test
	public void aufgabe3() throws CustomerServiceException {
		processCRUDOperations();			
	}

	@Test
	public void aufgabe4and5() throws CustomerServiceException {
		processCRUDOperations();
	}
	
	@Test
	public void aufgabe6() {
		Customer c = service.getById(1L);
		assertNotNull(c);
	}
	
	@Test
	public void aufgabe7() {
		Customer c = new Customer("Hugo", null);
		try {
			c = service.saveOrUpdateCustomer(c);
		} catch (Exception e) {
			// do nothing
		}
		
		// no new customer should be added, 
		// exception should be thrown to force a rollback	
		List<Customer> customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(4, customers.size());
	}
	
	@Test(expected = CustomerServiceException.class)
	public void aufgabe8() throws CustomerServiceException {
		Customer c = new Customer("Hugo", null);
		c = service.saveOrUpdateCustomer(c);
	}	

	private void processCRUDOperations() throws CustomerServiceException {
		// CREATE
		Customer c = new Customer("Hugo", "Test");
		c = service.saveOrUpdateCustomer(c);
		
		List<Customer> customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(5, customers.size());
		
		// READ
		c = service.getById(c.getId());
		assertEquals("Test", c.getLastName());
		
		// UPDATE
		c.setLastName("Tester");
		service.saveOrUpdateCustomer(c);
		c = service.getById(c.getId());
		assertEquals("Tester", c.getLastName());
		
		// DELETE
		service.deleteCustomer(c);
		
		customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(4, customers.size());
	}	
}
