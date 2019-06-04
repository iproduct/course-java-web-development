package shopping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.BookDBController;

@WebServlet(name="/",urlPatterns={"/ShoppingServlet"})
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SHOPPING_VIEW = "/WEB-INF/jsp/EShop.jsp";
	public static final String CART_VIEW = "/WEB-INF/jsp/Cart.jsp";
	public static final String CHECKOUT_VIEW = "/WEB-INF/jsp/Checkout.jsp";

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		boolean invalidateSession = false;
		HttpSession session = req.getSession(true);
		if (session == null) {
			res.sendRedirect("http://localhost:8080/ShoppingCart/error.html");
			return;
		}
		@SuppressWarnings("unchecked")
		List<Book> cart = (List<Book>) session.getAttribute("shopping.shoppingcart");
		if(cart == null) {
			cart = new ArrayList<Book>(); // first order
			session.setAttribute("shopping.shoppingcart", cart);
		}
		BookDBController bookController = 
				(BookDBController)getServletContext().getAttribute("bookController");
		String action = req.getParameter("action");
		String nextView = SHOPPING_VIEW;
		if (action != null && action.length() > 0) {
			if (!action.equals("CHECKOUT")) {
				String bookIdStr = req.getParameter("bookId");
				long bookId = 0;
				if (bookIdStr != null && bookIdStr.trim().length() > 0) {
					try {
						bookId = Long.parseLong(bookIdStr);
					} catch (NumberFormatException e) {};
					if (bookId > 0) {
						if (action.equals("DELETE")) {
							for(Book b : cart)
								if(b.getId() == bookId){
									cart.remove(b);
									break;
								}
						} else if (action.equals("ADD")) {
							String quantityStr = req.getParameter("quantity");
							int quantity = 1;
							try {
								quantity = Integer.parseInt(quantityStr);
							} catch (NumberFormatException e) {};
							boolean bookInCart = false;
							for (Book b : cart) {
								if(b.getId() == bookId){
									b.setQuantity(b.getQuantity() + quantity);
									bookInCart = true;
									break;
								}	
							}
							if(!bookInCart){
								Book b = bookController.getBookById(bookId);
								b.setQuantity(quantity);
								cart.add(b);
							}
						}
					}
				}
			} else if (action.equals("CHECKOUT")) {
				float total = 0;
				for (Book b : cart) {
					double price = b.getPrice();
					int qty = b.getQuantity();
					total += (price * qty);
				}
				total += 0.005;
				String amount = new Float(total).toString();
				int n = amount.indexOf('.');
				amount = amount.substring(0, n + 3);
				req.setAttribute("amount", amount);
				nextView = CHECKOUT_VIEW;
				invalidateSession = true;
			}
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				nextView);
		rd.forward(req, res);
		if(invalidateSession) session.invalidate();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req,res);
	}

}
