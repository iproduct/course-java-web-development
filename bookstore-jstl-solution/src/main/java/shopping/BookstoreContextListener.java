package shopping;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import business.BookDBController;

@WebListener
public class BookstoreContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		BookDBController bdc = new BookDBController();
		sce.getServletContext().setAttribute("bookController", bdc);
		bdc.reload();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		((BookDBController)sce.getServletContext().getAttribute("bookController"))
			.close();
	}


}
