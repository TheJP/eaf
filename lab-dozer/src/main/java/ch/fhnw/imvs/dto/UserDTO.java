package ch.fhnw.imvs.dto;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
	private long id;
	private String name;
	private String preName;
	private Set<Long> rentals = new HashSet<Long>();

	public Set<Long> getRentals() {
		return rentals;
	}

	public void setRentals(Set<Long> rentals) {
		this.rentals = rentals;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String toString() {
		return String.format("UserDTO(%s, %s, %s, %s)", id, name, preName,
				rentals);
	}
}
