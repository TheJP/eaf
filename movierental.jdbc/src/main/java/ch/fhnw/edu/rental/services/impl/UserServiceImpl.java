package ch.fhnw.edu.rental.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import ch.fhnw.edu.rental.daos.MovieDao;
import ch.fhnw.edu.rental.daos.RentalDao;
import ch.fhnw.edu.rental.daos.UserDao;
import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;
import ch.fhnw.edu.rental.services.RentalServiceException;
import ch.fhnw.edu.rental.services.UserService;

@Component
public class UserServiceImpl implements UserService {
	private Log log = LogFactory.getLog(this.getClass());
	
	private UserDao userDao;
	private RentalDao rentalDao;
	private MovieDao movieDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setRentalDao(RentalDao rentalDao) {
		this.rentalDao = rentalDao;
	}

	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	@Override
	public User getUserById(Long id) throws RentalServiceException {
		User user = this.userDao.getById(id);
		return user;
	}
	
	@Override
	public List<User> getAllUsers() throws RentalServiceException {
		List<User> users = userDao.getAll();
		if (log.isDebugEnabled()) {
			log.debug("getAllUsers() done");
		}
		return users;
	}

	@Override
	public void saveOrUpdateUser(User user) throws RentalServiceException {
		userDao.saveOrUpdate(user);
		if (log.isDebugEnabled()) {
			log.debug("saved or updated user[" + user.getId() + "]");
		}
	}

	@Override
	public void deleteUser(User user) throws RentalServiceException {
		if (user == null) {
			throw new RentalServiceException("'user' parameter is not set!");
		}

		userDao.delete(user);	// if (user.getRentals().size()>0) associated rentals 
								// have to be deleted by userDao.delete(user) as well
		if (log.isDebugEnabled()) {
			log.debug("user[" + user.getId() + "] deleted");
		}
	}

	@Override
	public List<User> getUsersByName(String name) throws RentalServiceException {
		List<User> users = userDao.getByName(name);
		return users;
	}

	@Override
	public Rental rentMovie(User user, Movie movie, int days)
			throws RentalServiceException {
		if (user == null) 
			throw new IllegalArgumentException("parameter 'user' is null!");
		if (movie == null) 
			throw new IllegalArgumentException("parameter 'movie' is null!");
		if (days < 1)
			throw new IllegalArgumentException("parameter 'days' must be > 0");

		Rental rental = new Rental(user, movie, days);
		rentalDao.saveOrUpdate(rental);

		// the constructor of rental changed the movie to rented, this change must
		// be persisted.
		movieDao.saveOrUpdate(movie);

		return rental;
	}
	
	@Override
	public void returnMovie(User user, Movie movie)
			throws RentalServiceException {
		if (user == null) 
			throw new IllegalArgumentException("parameter 'user' is null!");
		if (movie == null) 
			throw new IllegalArgumentException("parameter 'movie' is null!");

		Rental rentalToRemove = null;
		for (Rental rental : user.getRentals()) {
			if (rental.getMovie().equals(movie)) {
				rentalToRemove = rental;
				break;
			}
		}
		
		user.getRentals().remove(rentalToRemove);
		rentalToRemove.getMovie().setRented(false);
		rentalDao.delete(rentalToRemove);
	}
}
