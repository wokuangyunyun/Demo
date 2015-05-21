package com.struts1x;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginAction extends Action {
   @Override
public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	LoginForm loginForm = (LoginForm) form;
    String username = loginForm.getUsername();
    if("admin".equals(username)){
    	return mapping.findForward("success");
    }
	return mapping.findForward("error");
}
}
