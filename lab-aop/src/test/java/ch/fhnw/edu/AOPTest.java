package ch.fhnw.edu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.fhnw.edu.aop.ExpressionLanguageAspect;
import ch.fhnw.edu.domain.model.Customer;
import ch.fhnw.edu.domain.service.CustomerService;
import ch.fhnw.edu.domain.service.CustomerServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfiguration.class})
// @DirtiesContext forces reloading the spring context after each test
// USE IT WITH CARE (slows down the test suite!)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AOPTest {
	@Autowired CustomerService service;
	@Autowired ExpressionLanguageAspect expressionLanguageAspect;
	
	@Test
	public void aufgabe1() throws CustomerServiceException {
		processCRUDOperations();
		expressionLanguageAspect.logCounter();
	}
	
	@Test
	public void aufgabe2() throws CustomerServiceException {
		service.getById(new Long(1));
	}
	
	@Test(expected=Exception.class)
	public void aufgabe2ForShowException() throws CustomerServiceException {
		service.getById(null);
	}
	
	@Test
	public void aufgabe3() throws CustomerServiceException {
		Customer c = service.getById(new Long(1));
		assertEquals("GUGUS", c.getLastName());
	}
	
	@Test(expected=Exception.class)
	public void aufgabe3ForShowException() throws CustomerServiceException {
		service.getById(null);
	}
	
	private void processCRUDOperations() throws CustomerServiceException {
		// CREATE
		System.out.println("--- Perform CREATE...");
		Customer c = new Customer("Hugo", "Test");
		c = service.saveOrUpdateCustomer(c);
		
		List<Customer> customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(5, customers.size());
		
		// READ
		System.out.println("--- Perform READ...");
		c = service.getById(c.getId());
		assertEquals("Test", c.getLastName());
		
		// UPDATE
		System.out.println("--- Perform UPDATE...");
		c.setLastName("Tester");
		service.saveOrUpdateCustomer(c);
		c = service.getById(c.getId());
		assertEquals("Tester", c.getLastName());
		
		// DELETE
		System.out.println("--- Perform DELETE...");
		service.deleteCustomer(c);
		
		customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(4, customers.size());
	}		
}
