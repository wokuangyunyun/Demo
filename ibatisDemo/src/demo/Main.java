package demo;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import entity.Person;

public class Main {

	public static void main(String[] args) {
		SqlMapClient sqlMapClient = null;
		try {
			Reader reader = Resources
					.getResourceAsReader("SqlMapConfig.xml");
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Person person = null;
		try {
			person = (Person) sqlMapClient.queryForObject("selectPersonById","402881474d6b262d014d6b2633530000");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(person);
	}

}
