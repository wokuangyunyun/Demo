package demo;

import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanDemo {
	public static void main(String[] args) throws SQLException {
	  ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationcontext.xml");
      ac.registerShutdownHook();
      ConectionProvider cp = (ConectionProvider) ac.getBean("cp");
      System.out.println(cp.getConnection());
	}
}
