package com.struts1x;

import java.io.IOException;  

import javax.servlet.jsp.JspException;  
import javax.servlet.jsp.JspWriter;  
import javax.servlet.jsp.tagext.BodyContent;  
import javax.servlet.jsp.tagext.BodyTagSupport;  
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;  

public class HelloTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setBodyContent(BodyContent b) {
		try {
			b.append('c');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doInitBody() throws JspException {
		System.out.println("doInitBody");
	}

	@Override
	public int doAfterBody() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
	}

	@Override
	public BodyContent getBodyContent() {
		return super.getBodyContent();
	}

	@Override
	public JspWriter getPreviousOut() {
		return super.getPreviousOut();
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		try {
			this.name = (String) ExpressionEvaluatorManager.evaluate("name",
					name.toString(), Object.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();
		try {
			out.println("hello---------" + name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		release();
		return EVAL_PAGE;
	}

}
