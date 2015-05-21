package demo;

import com.jfinal.core.Controller;

public class UserController extends Controller {

	private String username;
	private String password;
	
	
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


	public void login() {
		if(getPara("username").equals("admin")){
			renderText("µÇÂ½³É¹¦£¡");
		}else{
			renderText("µÇÂ¼Ê§°Ü£¡");
		}
	}
}
