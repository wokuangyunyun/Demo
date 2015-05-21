package demo;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class StudentInterceptor implements Interceptor{

	@Override
	public void intercept(ActionInvocation ai) {
        System.out.println("studentAop++++++");
        ai.invoke();
        System.out.println("studentAop------");
	}



}
