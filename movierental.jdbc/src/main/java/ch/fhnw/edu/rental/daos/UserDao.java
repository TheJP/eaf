package ch.fhnw.edu.rental.daos;

import java.util.List;

import ch.fhnw.edu.rental.model.User;

public interface UserDao extends Dao<User> {
	List<User> getByName(String name);
}
