
package com.jfspecial.jfinal.component.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfspecial.util.Config;

/**
 * 设置用户session公共属性
 * 
 * 2018年11月19日 下午11:18:02 ljk
 */
public class ExceptionInterceptor implements Interceptor {

	private final static Log log = Log.getLog(ExceptionInterceptor.class);

	public void intercept(Invocation ai) {

		try {
			ai.invoke();
		} catch (Exception e) {
			log.error("异常：", e);
			Controller controller = ai.getController();
			controller.setAttr("error", e.toString());
			controller.render(Config.getStr("PAGES.500"));
		}

	}
}
