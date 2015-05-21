package com.JCaptcha;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitActionServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String jcaptcha = req.getParameter("jcaptcha");
		String captchaId = req.getSession().getId();
	    boolean flag = CaptchaServiceSingleton.getInstance().validateResponseForID(captchaId, jcaptcha);
	    resp.setContentType("text/html");
	    resp.setCharacterEncoding("utf-8");
	    PrintWriter writer = resp.getWriter();
	    StringBuffer sb = new StringBuffer();
	    if (flag) {
	    sb.append("<html><body>��֤�ɹ�</body></html>");
	    System.out.println("��֤�ɹ�");
	    } else {
	    sb.append("<html><body><font color='red'>��֤ʧ��</font></body></html>");
	    System.out.println("��֤ʧ��");
	    }
	    writer.println(sb.toString());
	    writer.close();
	}
}
