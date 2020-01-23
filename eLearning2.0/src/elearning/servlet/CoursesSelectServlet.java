package elearning.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreservlets.ServletUtilities;

import java.util.regex.Pattern;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/WEB-INF/view/CoursesSelectServlet")
public class CoursesSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Select Courses</title></head><body>");
		out.println("<h1>Welcome, " + session.getAttribute("currentUser") + "</h1>");
		out.println("<h2>Please select courses to study:</h2>");
		String courseSelectUrl = response.encodeURL(request.getContextPath()+"/portal?action=" + PortalServlet.COURSES_SELECT);
		out.println("<a href='" + courseSelectUrl + "'>Select Courses</a><br />");
		String courseCheckoutUrl = response.encodeURL(request.getContextPath()+"/portal?action=" + PortalServlet.COURSES_CHECKOUT);
		out.println("<a href='" + courseCheckoutUrl + "'>Checkout Selected Courses</a><br />");
		String logoutUrl = response.encodeURL(request.getContextPath()+"/portal?action=" + PortalServlet.LOGOUT);
		out.println("<a href='" + logoutUrl + "'>Logout</a><br />");
		out.println("</body></html>");		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
