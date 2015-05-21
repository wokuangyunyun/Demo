package demo;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConectionProvider {
	
	DriverManagerDataSource ds;
	
	public DriverManagerDataSource getDs() {
		return ds;
	}



	public void setDs(DriverManagerDataSource ds) {
		this.ds = ds;
	}



	public Connection getConnection() throws SQLException{
			return ds.getConnection();
	}
	
	public void init(){
		System.out.println("init........");
	}
	
	public void destroy(){
		System.out.println("destroy........");
	}
}
