package demo;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class StudentValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		validateRequired("student.name", "nameMsg", "���ֱ���");
	}

	@Override
	protected void handleError(Controller c) {
	    c.keepPara("student.name");
	    c.render("/add.jsp");
	}

}
