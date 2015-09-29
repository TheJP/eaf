package ch.fhnw.edu.rental.daos.jdbc;

import static ch.fhnw.edu.rental.daos.jdbc.JdbcTemplateUtils.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import ch.fhnw.edu.rental.daos.MovieDao;
import ch.fhnw.edu.rental.daos.RentalDao;
import ch.fhnw.edu.rental.daos.UserDao;
import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;

public class JdbcRentalDao extends JdbcDaoSupport implements RentalDao {

	private MovieDao movieDao;
	private UserDao userDao;

	private Map<Long, Rental> rentals = new HashMap<>();

	private RowMapper<Rental> get = (rs, row) -> {
		Long id = rs.getLong("RENTAL_ID");
		Rental rental = rentals.containsKey(id) ? rentals.get(id) : new Rental();
		rental.setRentalDate(rs.getDate("RENTAL_RENTALDATE"));
		rental.setRentalDays(rs.getInt("RENTAL_RENTALDAYS"));
		rental.setMovie(movieDao.getById(rs.getLong("MOVIE_ID")));
		rental.setUser(userDao.getById(rs.getLong("USER_ID")));
		rentals.put(id, rental);
		return rental;
	};

	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Rental getById(Long id) {
		return getJdbcTemplate().queryForObject("SELECT * FROM RENTALS WHERE RENTAL_ID = ?", get, id);
	}

	@Override
	public List<Rental> getAll() {
		return getJdbcTemplate().query("SELECT * FROM RENTALS", get);
	}

	@Override
	public List<Rental> getRentalsByUser(User user) {
		return getJdbcTemplate().query("SELECT * FROM RENTALS WHERE USER_ID = ?", get, user.getId());
	}

	@Override
	public void saveOrUpdate(Rental rental) {
		if(rental.getId() == null){
			Long id = update(
				getJdbcTemplate(),
				"INSERT INTO RENTALS (RENTAL_RENTALDATE, RENTAL_RENTALDAYS, MOVIE_ID, USER_ID) VALUES (?, ?, ?, ?)", "RENTAL_ID",
				(ps) -> {
					try {
						ps.setDate(1, new java.sql.Date(rental.getRentalDate().getTime()));
						ps.setInt(2, rental.getRentalDays());
						ps.setLong(3, rental.getMovie().getId());
						ps.setLong(4, rental.getUser().getId());
					} catch (Exception e) { }
				});
			rental.setId(id);
			rentals.put(id, rental);
		} else {
			getJdbcTemplate().update(
				"UPDATE RENTALS SET RENTAL_RENTALDATE = ?, RENTAL_RENTALDAYS = ?, MOVIE_ID = ?, USER_ID = ? WHERE RENTAL_ID = ?",
				rental.getRentalDate(), rental.getRentalDays(), rental.getMovie().getId(), rental.getUser().getId());
			rentals.put(rental.getId(), rental);
		}
	}

	@Override
	public void delete(Rental rental) {
		getJdbcTemplate().update("DELETE FROM RENTALS WHERE RENTAL_ID = ?", rental.getId());
		rental.setDeleted(true);
		rentals.remove(rental.getId());
	}

}
