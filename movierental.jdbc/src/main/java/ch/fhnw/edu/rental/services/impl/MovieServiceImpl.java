package ch.fhnw.edu.rental.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import ch.fhnw.edu.rental.daos.MovieDao;
import ch.fhnw.edu.rental.daos.PriceCategoryDao;
import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.model.PriceCategory;
import ch.fhnw.edu.rental.services.MovieService;
import ch.fhnw.edu.rental.services.RentalServiceException;

@Component
public class MovieServiceImpl implements MovieService {
	private Log log = LogFactory.getLog(this.getClass());
	
	private MovieDao movieDao;
	private PriceCategoryDao priceCategoryDao;


	public void setPriceCategoryDao(PriceCategoryDao priceCategoryDao) {
		this.priceCategoryDao = priceCategoryDao;
	}

	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	public Movie getMovieById(Long id) throws RentalServiceException {
		return movieDao.getById(id);
	}

	public List<Movie> getAllMovies() throws RentalServiceException {
		List<Movie> movies = movieDao.getAll();
		if (log.isDebugEnabled()) {
			log.debug("getAllMovies() done");
		}
		return movies;
	}

	public List<Movie> getMoviesByTitle(String title) throws RentalServiceException {
		return movieDao.getByTitle(title);
	}

	public void saveOrUpdateMovie(Movie movie) throws RentalServiceException {
		if (movie == null) {
			throw new RentalServiceException("'movie' parameter is not set!");
		}
		movieDao.saveOrUpdate(movie);
		if (log.isDebugEnabled()) {
			log.debug("saved or updated movie[" + movie.getId() + "]");
		}
	}

	public void deleteMovie(Movie movie) throws RentalServiceException {
		if (movie == null) {
			throw new RentalServiceException("'movie' parameter is not set!");
		}
		if (movie.isRented()) {
			throw new RentalServiceException("movie is still used");
		}

		movieDao.delete(movie);
		
		if (log.isDebugEnabled()) {
			log.debug("movie[" + movie.getId() + "] deleted");
		}
	}

	public List<PriceCategory> getAllPriceCategories() throws RentalServiceException {
		return priceCategoryDao.getAll();
	}

}
