package ch.fhnw.edu.rental.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import ch.fhnw.edu.rental.daos.MovieDao;
import ch.fhnw.edu.rental.daos.RentalDao;
import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.services.RentalService;
import ch.fhnw.edu.rental.services.RentalServiceException;

@Component
public class RentalServiceImpl implements RentalService {
	private Log log = LogFactory.getLog(this.getClass());
	
	private RentalDao rentalDao;
	
	public void setRentalDao(RentalDao rentalDao) {
		this.rentalDao = rentalDao;
	}

	private MovieDao movieDao;
	
	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	@Override
	public List<Rental> getAllRentals() throws RentalServiceException {
		List<Rental> rentals = rentalDao.getAll();
		if (log.isDebugEnabled()) {
			log.debug("getAllRentals() done");
		}
		return rentals;
	}

	@Override
	public int calcRemainingDaysOfRental(Rental rental, Date date) {
		return rental.calcRemainingDaysOfRental(date);
	}

	@Override
	public Rental getRentalById(Long id) {
		return rentalDao.getById(id);
	}

	@Override
	public void deleteRental(Rental rental) throws RentalServiceException {
		if (rental == null) {
			throw new RentalServiceException("'rental' parameter is not set!");
		}

		rental.getUser().getRentals().remove(rental);
		rental.getMovie().setRented(false);

		rentalDao.delete(rental);
		
		movieDao.saveOrUpdate(rental.getMovie());
		
		if (log.isDebugEnabled()) {
			log.debug("rental[" + rental.getId() + "] deleted");
		}		
	}
}
