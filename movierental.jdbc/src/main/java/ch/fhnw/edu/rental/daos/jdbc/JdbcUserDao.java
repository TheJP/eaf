package ch.fhnw.edu.rental.daos.jdbc;

import java.util.List;

import javax.sql.DataSource;

import ch.fhnw.edu.rental.daos.RentalDao;
import ch.fhnw.edu.rental.daos.UserDao;
import ch.fhnw.edu.rental.model.User;

public class JdbcUserDao implements UserDao {

	@SuppressWarnings("unused")
	private DataSource ds;

	@SuppressWarnings("unused")
	private RentalDao rentalDao;

	public void setDataSource(DataSource dataSource) {
		this.ds = dataSource;
	}

	public void setRentalDao(RentalDao rentalDao) {
		this.rentalDao = rentalDao;
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public List<User> getByName(String name) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void saveOrUpdate(User user) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
