package ch.fhnw.edu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;
	@Column(name = "user_name")
	private String lastName;
	@Column(name = "user_firstname")
	private String firstName;
	@Column(name = "user_email")
	private String email;

	public User(String lastName, String firstName, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
	}

	public User() {	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}