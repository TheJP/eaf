package ch.fhnw.imvs.model;

import java.util.HashSet;
import java.util.Set;

public class User {
	private Long id;
	private String name;
	private String preName;

	private Set<Rental> rentals = new HashSet<Rental>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public Set<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(Set<Rental> rentals) {
		this.rentals = rentals;
	}
}
