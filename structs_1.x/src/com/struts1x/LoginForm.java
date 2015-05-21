package com.struts1x;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	@Override
	public ActionErrors validate(ActionMapping map, HttpServletRequest request) {
		// TODO Auto-generated method stub
		ActionErrors ae = new ActionErrors();
		if(username.length()<6){
			ae.add("userName",new ActionMessage("error.login.username",username));
		}
		if(password.length()<5){
			ae.add("password",new ActionMessage("error.login.password",password));
		}
		return ae;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
