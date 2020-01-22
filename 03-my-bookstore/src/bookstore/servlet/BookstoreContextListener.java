package bookstore.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import bookstore.dao.BookDBController;

@WebListener
public class BookstoreContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		BookDBController bdc = new BookDBController();
		try {
			bdc.init();
			sce.getServletContext().setAttribute("bookController", bdc);
			bdc.reload();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}


}
