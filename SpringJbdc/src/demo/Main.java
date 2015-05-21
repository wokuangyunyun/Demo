package demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationcontext.xml");
		StudentService ss = (StudentService) ac.getBean("studentService");
		Student st = new Student();
		st.setId(8);
		st.setName("hurry");
		st.setAge(15);
		ss.save(st);
	}
}
