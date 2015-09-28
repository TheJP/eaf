package ch.fhnw.edu.rental.daos.mem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.fhnw.edu.rental.daos.RentalDao;
import ch.fhnw.edu.rental.daos.UserDao;
import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;

public class MemoryUserDao implements UserDao {

	private Map<Long,User> data = new HashMap<Long,User>();
	private long nextId = 1;

	private RentalDao rentalDao;

	public void setRentalDao(RentalDao rentalDao) {
		this.rentalDao = rentalDao;
	}

	@SuppressWarnings("unused")
	private void initData () {
		data.clear();
		nextId = 1;
		saveOrUpdate(new User("Keller", "Marc"));
		saveOrUpdate(new User("Knecht", "Werner"));
		saveOrUpdate(new User("Meyer", "Barbara"));
		saveOrUpdate(new User("Kummer", "Adolf"));

		getById(1L).setEmail("marc.keller@gmail.com");
		getById(2L).setEmail("werner.knecht@gmail.com");
		getById(3L).setEmail("barbara.meyer@gmail.com");
		getById(4L).setEmail("adolf.kummer@gmail.com");
	}

	@Override
	public void delete(User user) {
		for(Rental r : user.getRentals()){
			rentalDao.delete(r);
		}
		data.remove(user.getId());
		user.setId(null);
	}

	@Override
	public List<User> getAll() {
		return new ArrayList<User>(data.values());
	}

	@Override
	public User getById(Long id) {
		return data.get(id);
	}

	@Override
	public List<User> getByName(String name) {
		List<User> result = new ArrayList<User>();
		for(User u : data.values()){
			if(u.getLastName().equals(name)) result.add(u);
		}
		return result;
	}

	@Override
	public void saveOrUpdate(User user) {
		if(user.getId()==null) user.setId(nextId++);
		data.put(user.getId(), user);
	}

}
