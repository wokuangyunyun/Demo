package demo;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class StudentService {

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	// 设置数据源
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void save(Student student){
		jdbcTemplate.update("insert into student values(?,?,?,'1001')",new Object[]{student.getId(),student.getName(),student.getAge()}, new int[]{java.sql.Types.INTEGER,java.sql.Types.VARCHAR,java.sql.Types.INTEGER});
	}
}
