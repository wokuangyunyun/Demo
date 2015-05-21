package com.struts1x;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.struts.Globals;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class LocaleFilter implements Filter {
   
	
	public static final String CHINESELANGUAGE = "中文(简体)";
	public static final String ENGLISHLANGUAGE = "English";
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Locale currentLocale = null; //定义语言地区信息 
		String language = null;//客户端页面写的语言cookies
		HttpServletRequest request = (HttpServletRequest)arg0;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for(Cookie cookie : cookies){ 
	        	if(cookie.getName().equals("xxx_language")){
	        		language=cookie.getValue();//判断和设置cookies里面是否有值。
	        	}
	        } 
		}
		try {
			
			/**
			 * 1.如果cookies中是有值的（前提条件是从页面传过来的语言为空才进下面的逻辑）
			 */
			 if(language != null){
				if ("zh_CN".equals(language)) {//如果cookies中的语言为中文
					currentLocale = new Locale("zh", "CN");
					language = "zh_CN";
				    request.getSession().setAttribute("lan", CHINESELANGUAGE);
				} else if ("en_US".equals(language)) {//如果cookies中的语言为英文
					currentLocale = new Locale("en", "US"); 
					language = "en_US";
				    request.getSession().setAttribute("lan", ENGLISHLANGUAGE);
				} else {
					currentLocale = new Locale("zh", "CN");
					language = "zh_CN";
				    request.getSession().setAttribute("lan", CHINESELANGUAGE);
				}
			}
			/**
			 * 传过来的语言：如果既没有传过来的语言和cookies里面的值就根据浏览器默认的语言来显示
			 */
			else {
				String defaultLanguage = request.getLocale().toString(); //浏览器默认的语言
				if(defaultLanguage.equals("en_US")){
				    currentLocale = new Locale("en", "CN");
				    request.getSession().setAttribute("lan", ENGLISHLANGUAGE);
				}else{
					currentLocale = new Locale("zh", "CN");
				    request.getSession().setAttribute("lan", CHINESELANGUAGE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			///最后将设置好的环境类放到session中去
			request.getSession().removeAttribute(Globals.LOCALE_KEY);
			request.getSession().setAttribute(Globals.LOCALE_KEY, currentLocale);
			}
        
        chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
