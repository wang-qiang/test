package test.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import fuc.common.web.listener.ApplicationListenerAdapter;

public class ApplicationListener extends ApplicationListenerAdapter {

	public static final String BASE = "base";
	
	public void contextInitialized(ServletContextEvent event) {
		//根路径
		ServletContext application = event.getServletContext();
		String root = application.getContextPath();
		application.setAttribute(BASE, root);
	}
	
}