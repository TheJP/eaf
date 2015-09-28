package ch.fhnw.edu.rental.daos;

import java.util.List;

import ch.fhnw.edu.rental.model.Movie;

public interface MovieDao extends Dao<Movie> {
	List<Movie> getByTitle(String name);
}
