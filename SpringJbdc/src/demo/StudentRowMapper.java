package demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper<Student>{

	@Override
	public Student mapRow(ResultSet set, int index) throws SQLException {

		return new Student(set.getLong("id"),set.getString("name"),set.getInt("age"));
	}

}
