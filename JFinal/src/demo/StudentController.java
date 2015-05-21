package demo;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class StudentController extends Controller {
	
	@Before(StudentInterceptor.class)
	public void list() {
		List<Student> students = Student.dao.find("select*from student");
		setAttr("students", students);
		render("/list.jsp");
	}

	public void add() {
		render("/add.jsp");
	}
	
	public void get(){
		Student student = Student.dao.findById(getParaToInt());
		setAttr("student", student);
		render("/edit.jsp");
	}
	
	public void delete(){
		Student student = Student.dao.findById(getParaToInt());
		student.delete();
		forwardAction("/student/list");
	}
	
	public void update(){
		Student student = getModel(Student.class);
		student.update();
		forwardAction("/student/list");
	}
	
	@Before(StudentValidator.class)
	public void save(){
		Student student = getModel(Student.class);
		student.save();
		forwardAction("/student/list");
	}
}
