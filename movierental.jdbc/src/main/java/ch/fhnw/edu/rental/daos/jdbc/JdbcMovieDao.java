package ch.fhnw.edu.rental.daos.jdbc;

import static ch.fhnw.edu.rental.daos.jdbc.JdbcTemplateUtils.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import ch.fhnw.edu.rental.daos.MovieDao;
import ch.fhnw.edu.rental.daos.PriceCategoryDao;
import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.model.PriceCategoryChildren;

public class JdbcMovieDao extends JdbcDaoSupport implements MovieDao {

	private PriceCategoryDao priceCategoryDao;

	private Map<Long, Movie> movies = new HashMap<>();

	private RowMapper<Movie> get = (rs, row) -> {
		Long id = rs.getLong("MOVIE_ID");
		Movie movie = movies.containsKey(id) ? movies.get(id) : new Movie(rs.getString("MOVIE_TITLE"), rs.getDate("MOVIE_RELEASEDATE"), new PriceCategoryChildren());
		movie.setPriceCategory(priceCategoryDao.getById(rs.getLong("PRICECATEGORY_FK")));
		movie.setId(id);
		movie.setRented(rs.getBoolean("MOVIE_RENTED"));
		movies.put(id, movie);
		return movie;
	};

	public void setPriceCategoryDao(PriceCategoryDao priceCategoryDao) {
		this.priceCategoryDao = priceCategoryDao;
	}

	@Override
	public Movie getById(Long id) {
		return getJdbcTemplate().queryForObject("SELECT * FROM MOVIES WHERE MOVIE_ID = ?", get, id);
	}

	@Override
	public List<Movie> getByTitle(String title) {
		return getJdbcTemplate().query("SELECT * FROM MOVIES WHERE MOVIE_TITLE = ?", get, title);
	}

	@Override
	public List<Movie> getAll() {
		return getJdbcTemplate().query("SELECT * FROM MOVIES", get);
	}

	@Override
	public void saveOrUpdate(Movie movie) {
		if(movie.getId() == null){
			Long id = update(
				getJdbcTemplate(),
				"INSERT INTO MOVIES (MOVIE_TITLE, MOVIE_RELEASEDATE, MOVIE_RENTED, PRICECATEGORY_FK) VALUES (?, ?, ?, ?)", "MOVIE_ID",
				(ps) -> {
					try {
						ps.setString(1, movie.getTitle());
						ps.setDate(2, movie.getReleaseDate() == null ? null : new java.sql.Date(movie.getReleaseDate().getTime()));
						ps.setBoolean(3, movie.isRented());
						ps.setLong(4, movie.getPriceCategory() == null ? null : movie.getPriceCategory().getId());
					} catch (Exception e) { }
				});
			movie.setId(id);
			movies.put(id, movie);
		} else {
			getJdbcTemplate().update(
				"UPDATE MOVIES SET MOVIE_TITLE = ?, MOVIE_RELEASEDATE = ?, MOVIE_RENTED = ?, PRICECATEGORY_FK = ? WHERE MOVIE_ID = ?",
				movie.getTitle(), movie.getReleaseDate(), movie.isRented(), movie.getPriceCategory().getId(), movie.getId());
			movies.put(movie.getId(), movie);
		}
	}

	@Override
	public void delete(Movie movie) {
		getJdbcTemplate().update("DELETE FROM MOVIES WHERE MOVIE_ID = ?", movie.getId());
		movie.setDeleted(true);
		movies.remove(movie.getId());
		movie.setId(null);
	}

}
