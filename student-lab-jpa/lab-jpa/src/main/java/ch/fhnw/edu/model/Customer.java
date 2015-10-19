package ch.fhnw.edu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Customer {

	protected Customer() {
	}

	public Customer(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	@OneToOne
	private Address address;

	private int age;

	public int getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(int id) {
		System.out.println("setId called");
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		if (address == null)
			return String.format("%-10s [%d]", name, age);
		else
			return String.format("%-10s [%d], %s, %s", name, age, address
					.getStreet(), address.getCity());
	}

	public void addOrder(Order order) {

	}

}
