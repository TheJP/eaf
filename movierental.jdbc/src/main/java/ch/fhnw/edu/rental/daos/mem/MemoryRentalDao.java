package ch.fhnw.edu.rental.daos.mem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.fhnw.edu.rental.daos.MovieDao;
import ch.fhnw.edu.rental.daos.RentalDao;
import ch.fhnw.edu.rental.daos.UserDao;
import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;

public class MemoryRentalDao implements RentalDao {

	private Map<Long, Rental> data = new HashMap<Long, Rental>();
	private long nextId = 1;

	private UserDao userDao;
	private MovieDao movieDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	@SuppressWarnings("unused")
	private void initData () {
		data.clear();
		nextId = 1;
		Calendar cal = new GregorianCalendar(2015, GregorianCalendar.SEPTEMBER, 23);
		Rental r = new Rental(userDao.getById(1L), movieDao.getById(1L), 7, cal.getTime());
		saveOrUpdate(r);
		cal = new GregorianCalendar(2015, GregorianCalendar.SEPTEMBER, 25);
		r = new Rental(userDao.getById(1L), movieDao.getById(2L), 6, cal.getTime());
		saveOrUpdate(r);
		cal = new GregorianCalendar(2015, GregorianCalendar.SEPTEMBER, 27);
		r = new Rental(userDao.getById(3L), movieDao.getById(3L), 6, cal.getTime());
		saveOrUpdate(r);
	}

	@Override
	public Rental getById(Long id) {
		return data.get(id);
	}

	@Override
	public List<Rental> getAll() {
		return new ArrayList<Rental>(data.values());
	}

	@Override
	public List<Rental> getRentalsByUser(User user) {
		List<Rental> res = new ArrayList<Rental>();
		for(Rental r : data.values()){
			if(r.getUser().equals(user)) res.add(r);
		}
		return res;
	}

	@Override
	public void saveOrUpdate(Rental rental) {
		if (rental.getId() == null) rental.setId(nextId++);
		data.put(rental.getId(), rental);
	}

	@Override
	public void delete(Rental rental) {
		data.remove(rental.getId());
		rental.setId(null);
	}
}
