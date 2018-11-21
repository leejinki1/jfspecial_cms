package com.jfspecial.component.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfspecial.component.util.JFSpecialUtils;

import java.util.UUID;

/**
 * 用户认证拦截器
 * 
 * @author flyfox 2014-2-11
 */
public class UserKeyInterceptor implements Interceptor {


	public void intercept(Invocation ai) {

		Controller controller = ai.getController();
		
		// 如果没有，就设置一个
		Object key = controller.getSessionAttr(JFSpecialUtils.USER_KEY);
		if (key==null) {
			controller.setSessionAttr(JFSpecialUtils.USER_KEY, UUID.randomUUID().toString());
		}

		ai.invoke();
	}
}
