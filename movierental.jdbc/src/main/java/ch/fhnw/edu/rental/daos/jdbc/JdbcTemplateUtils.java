package ch.fhnw.edu.rental.daos.jdbc;

import java.sql.PreparedStatement;
import java.util.function.Consumer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class JdbcTemplateUtils {

	public static Long update(JdbcTemplate template, String sql, String pk, Consumer<PreparedStatement> psParams){
		KeyHolder key = new GeneratedKeyHolder();
		template.update((connection) -> {
			PreparedStatement ps = connection.prepareStatement(sql, new String[]{ pk });
			psParams.accept(ps);
			return ps;
		}, key);
		return (Long) key.getKey();
	}

}
