package ch.fhnw.edu.rental.daos;

import java.util.List;

public interface Dao<T> {
	T getById(Long id);
	List<T> getAll();
	void saveOrUpdate(T t);
	void delete(T t);
}
