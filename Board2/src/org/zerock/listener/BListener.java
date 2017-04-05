package org.zerock.listener;
//d
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import org.zerock.persistence.BoardDAO;

/**
 * Application Lifecycle Listener implementation class BListener
 *
 */
@WebListener
public class BListener implements ServletContextListener, ServletRequestListener {

	/**
	 * Default constructor.
	 */
	public BListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
	 */
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
	 */
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {

		ServletContext ctx = sce.getServletContext();

		System.out.println("1----------------------------------");
		
		try {
			ctx.setAttribute("BoardDAO", new BoardDAO());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
