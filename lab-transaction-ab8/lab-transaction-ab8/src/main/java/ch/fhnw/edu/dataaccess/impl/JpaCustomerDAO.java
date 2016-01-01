package ch.fhnw.edu.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import ch.fhnw.edu.dataaccess.CustomerDAO;
import ch.fhnw.edu.domain.model.Customer;


@Component
public class JpaCustomerDAO implements CustomerDAO {
	@PersistenceContext
	protected EntityManager em;
	
	public Customer getById(Long id) {
		return em.find(Customer.class, id);
	}

	public List<Customer> getByName(String name) {
		TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer c",	Customer.class);
		q.setParameter("name", name);
		return q.getResultList();
	}

	public void delete(Customer customer) {
		em.remove(customer);
	}

	public List<Customer> getAll() {
		TypedQuery<Customer> q = em.createQuery("FROM Customer", Customer.class);
		List<Customer> customers = q.getResultList();
		return customers;
	}

	public Customer saveOrUpdate(Customer customer) {
		if (customer.getId() != null) {
			em.merge(customer);
		} else {
			em.persist(customer);
		}
		return customer;
	}

	@Override
	public Customer manage(Customer customer) {
		return em.merge(customer);
	}

}
