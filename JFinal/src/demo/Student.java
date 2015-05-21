package demo;

import com.jfinal.plugin.activerecord.Model;

public class Student extends Model<Student> {

	/**
	 * 
	 */
	public static final Student dao = new Student();
	
	private static final long serialVersionUID = 1L;
	
	public Classes getClasses(){
		return Classes.dao.findById(get("classesid"));
	}

}
