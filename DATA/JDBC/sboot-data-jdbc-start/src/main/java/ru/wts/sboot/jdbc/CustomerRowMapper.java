package ru.wts.sboot.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
/**
 * for jdbcTemplate.
 * 
 * @author ayrat
 *
 */
public class CustomerRowMapper implements RowMapper<Customer>{

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer result = new Customer(rs.getString("first_name"), rs.getString("last_name"));
		result.setId(rs.getLong("id"));
		return result;
	}

}
