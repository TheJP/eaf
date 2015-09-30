package ch.fhnw.edu.rental.daos.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import ch.fhnw.edu.rental.daos.PriceCategoryDao;
import ch.fhnw.edu.rental.model.PriceCategory;
import ch.fhnw.edu.rental.model.PriceCategoryChildren;
import ch.fhnw.edu.rental.model.PriceCategoryNewRelease;
import ch.fhnw.edu.rental.model.PriceCategoryRegular;

public class JdbcPriceCategoryDao extends JdbcDaoSupport implements PriceCategoryDao {

	private Map<Long, PriceCategory> categories = new HashMap<>();

	private RowMapper<PriceCategory> get = (rs, row) -> {
		Long id = rs.getLong("PRICECATEGORY_ID");
		String type = rs.getString("PRICECATEGORY_TYPE");
		PriceCategory category = categories.containsKey(id) ? categories.get(id) : getCatByType(type);
		category.setId(id);
		return category;
	};

	private PriceCategory getCatByType(String type){
		switch(type){
			case "Regular": return new PriceCategoryRegular();
			case "Children": return new PriceCategoryChildren();
			case "NewRelease": return new PriceCategoryNewRelease();
			default: throw new IllegalArgumentException("Unknown category");
		}
	}

	@Override
	public PriceCategory getById(Long id) {
		return getJdbcTemplate().queryForObject("SELECT * FROM PRICECATEGORIES WHERE PRICECATEGORY_ID = ?", get, id);
	}

	@Override
	public List<PriceCategory> getAll() {
		return getJdbcTemplate().query("SELECT * FROM PRICECATEGORIES", get);
	}

	@Override
	public void saveOrUpdate(PriceCategory priceCategory) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(PriceCategory priceCategory) {
		throw new UnsupportedOperationException();
	}

}
