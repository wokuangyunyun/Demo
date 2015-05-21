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
   
	
	public static final String CHINESELANGUAGE = "����(����)";
	public static final String ENGLISHLANGUAGE = "English";
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Locale currentLocale = null; //�������Ե�����Ϣ 
		String language = null;//�ͻ���ҳ��д������cookies
		HttpServletRequest request = (HttpServletRequest)arg0;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for(Cookie cookie : cookies){ 
	        	if(cookie.getName().equals("xxx_language")){
	        		language=cookie.getValue();//�жϺ�����cookies�����Ƿ���ֵ��
	        	}
	        } 
		}
		try {
			
			/**
			 * 1.���cookies������ֵ�ģ�ǰ�������Ǵ�ҳ�洫����������Ϊ�ղŽ�������߼���
			 */
			 if(language != null){
				if ("zh_CN".equals(language)) {//���cookies�е�����Ϊ����
					currentLocale = new Locale("zh", "CN");
					language = "zh_CN";
				    request.getSession().setAttribute("lan", CHINESELANGUAGE);
				} else if ("en_US".equals(language)) {//���cookies�е�����ΪӢ��
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
			 * �����������ԣ������û�д����������Ժ�cookies�����ֵ�͸��������Ĭ�ϵ���������ʾ
			 */
			else {
				String defaultLanguage = request.getLocale().toString(); //�����Ĭ�ϵ�����
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
			///������úõĻ�����ŵ�session��ȥ
			request.getSession().removeAttribute(Globals.LOCALE_KEY);
			request.getSession().setAttribute(Globals.LOCALE_KEY, currentLocale);
			}
        
        chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
