package chapter08;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("ServletRequest����������");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("ServletRequest���󱻴�����");
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("HTTPSession���󱻴�����");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("HTTPSession����������");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContext������������");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContext���󱻴�����");
	}

}
