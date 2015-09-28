package ch.fhnw.edu.rental.daos.mem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.fhnw.edu.rental.daos.MovieDao;
import ch.fhnw.edu.rental.daos.PriceCategoryDao;
import ch.fhnw.edu.rental.model.Movie;

public class MemoryMovieDao implements MovieDao {

	private Map<Long, Movie> data = new HashMap<Long, Movie>();
	private long nextId = 1;

	private PriceCategoryDao priceCategoryDao;

	public void setPriceCategoryDao(PriceCategoryDao priceCategoryDao) {
		this.priceCategoryDao = priceCategoryDao;
	}

	@SuppressWarnings("unused")
	private void initData () {
		System.out.println("MemoryMovieDao: initData " + data.size() + " " + this);
		data.clear();
		nextId = 1;

		Calendar c = Calendar.getInstance();
		c.clear();

		c.set(2015, Calendar.MARCH, 31);
		saveOrUpdate(new Movie("Interstellar", c.getTime(), priceCategoryDao.getById(1L)));

		c.set(2015, Calendar.MARCH, 31);
		saveOrUpdate(new Movie("The Imitation Game", c.getTime(), priceCategoryDao.getById(1L)));

		c.set(2015, Calendar.APRIL, 28);
		saveOrUpdate(new Movie("Paddington", c.getTime(), priceCategoryDao.getById(2L)));

		c.set(2015, Calendar.MAY, 8);
		saveOrUpdate(new Movie("Fifty Shades of Grey", c.getTime(), priceCategoryDao.getById(3L)));

		c.set(2015, Calendar.JULY, 14);
		saveOrUpdate(new Movie("Ex Machina", c.getTime(), priceCategoryDao.getById(3L)));
	}

	@Override
	public void delete(Movie movie) {
		data.remove(movie.getId());
		movie.setId(null);
	}

	@Override
	public List<Movie> getAll() {
		return new ArrayList<Movie>(data.values());
	}

	@Override
	public Movie getById(Long id) {
		return data.get(id);
	}

	@Override
	public List<Movie> getByTitle(String name) {
		List<Movie> result = new ArrayList<Movie>();
		for(Movie m : data.values()){
			if(m.getTitle().equals(name)) result.add(m);
		}
		return result;
	}

	@Override
	public void saveOrUpdate(Movie movie) {
		if(movie.getId() == null){
			movie.setId(nextId++);
			data.put(movie.getId(), movie);
		}
		else {
			// Alternative: copy the new values over the old ones.
			data.put(movie.getId(), movie);
		}
	}

}
