package ch.fhnw.edu.rental.daos.jdbc;

import static ch.fhnw.edu.rental.daos.jdbc.JdbcTemplateUtils.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import ch.fhnw.edu.rental.daos.RentalDao;
import ch.fhnw.edu.rental.daos.UserDao;
import ch.fhnw.edu.rental.model.User;

public class JdbcUserDao extends JdbcDaoSupport implements UserDao {

	private RentalDao rentalDao;

	private Map<Long, User> users = new HashMap<>();

	private RowMapper<User> get = (rs, row) -> {
		Long id = rs.getLong("USER_ID");
		User user = users.containsKey(id) ? users.get(id) : new User("", "");
		user.setLastName(rs.getString("USER_NAME"));
		user.setFirstName(rs.getString("USER_FIRSTNAME"));
		user.setId(rs.getLong("USER_ID"));
		user.setEmail(rs.getString("USER_EMAIL"));
		user.setRentals(new LazyList<>(() -> rentalDao.getRentalsByUser(user), rentalDao));
		users.put(id, user);
		return user;
	};

	public void setRentalDao(RentalDao rentalDao) {
		this.rentalDao = rentalDao;
	}

	@Override
	public User getById(Long id) {
		return getJdbcTemplate().queryForObject("SELECT * FROM USERS WHERE USER_ID = ?", get, id);
	}

	@Override
	public List<User> getAll() {
		return getJdbcTemplate().query("SELECT * FROM USERS", get);
	}

	@Override
	public List<User> getByName(String name) {
		return getJdbcTemplate().query("SELECT * FROM USERS WHERE USER_NAME = ?", get, name);
	}

	@Override
	public void saveOrUpdate(User user) {
		if(user.getId() == null){
			Long id = update(
				getJdbcTemplate(),
				"INSERT INTO USERS (USER_NAME, USER_FIRSTNAME, USER_EMAIL) VALUES (?, ?, ?)", "USER_ID",
				(ps) -> {
					try {
						ps.setString(1, user.getLastName());
						ps.setString(2, user.getFirstName());
						ps.setString(3, user.getEmail());
					} catch (Exception e) { }
				});
			user.setId(id);
			user.setRentals(new LazyList<>(() -> rentalDao.getRentalsByUser(user), rentalDao));
			users.put(id, user);
		} else {
			getJdbcTemplate().update(
				"UPDATE USERS SET USER_NAME = ?, USER_FIRSTNAME = ?, USER_EMAIL = ? WHERE USER_ID = ?",
				user.getLastName(), user.getFirstName(), user.getEmail(), user.getId());
			users.put(user.getId(), user);
		}
	}

	@Override
	public void delete(User user) {
		if(user.getId() == null){ return; }
		user.getRentals().stream().forEach(rental -> rentalDao.delete(rental));
		getJdbcTemplate().update("DELETE FROM USERS WHERE USER_ID = ?", user.getId());
		user.setDeleted(true);
		users.remove(user.getId());
		user.setId(null);
	}

}
