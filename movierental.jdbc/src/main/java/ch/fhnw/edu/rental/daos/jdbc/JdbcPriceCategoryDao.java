package ch.fhnw.edu.rental.daos.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import ch.fhnw.edu.rental.daos.PriceCategoryDao;
import ch.fhnw.edu.rental.model.PriceCategory;
import ch.fhnw.edu.rental.model.PriceCategoryChildren;
import ch.fhnw.edu.rental.model.PriceCategoryNewRelease;
import ch.fhnw.edu.rental.model.PriceCategoryRegular;

public class JdbcPriceCategoryDao implements PriceCategoryDao {

	////////////// this is an in-memory implementation => to be replaced
	private Map<Long, PriceCategory> data = new HashMap<>();
	{
		PriceCategory pc = new PriceCategoryRegular();
		pc.setId(1L);
		data.put(1L, pc);

		pc = new PriceCategoryChildren();
		pc.setId(2L);;
		data.put(2L, pc);

		pc = new PriceCategoryNewRelease();
		pc.setId(3L);;
		data.put(3L, pc);
	}

	@Override
	public List<PriceCategory> getAll() {
		return new ArrayList<PriceCategory>(data.values());
	}

	@Override
	public PriceCategory getById(Long id) {
		return data.get(id);
	}
	////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unused")
	private DataSource ds;

	public void setDataSource(DataSource dataSource) {
		this.ds = dataSource;
	}

//	@Override
//	public PriceCategory getById(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<PriceCategory> getAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void saveOrUpdate(PriceCategory priceCategory) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(PriceCategory priceCategory) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
