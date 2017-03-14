package util;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class BeanFactory {
	public static WebApplicationContext wac;

	public static Object getbean(String beanid) {
		if (wac == null) {
			wac = ContextLoader.getCurrentWebApplicationContext();
			System.out.println(BeanFactory.wac);
		}
		return wac.getBean(beanid);
	}
}
