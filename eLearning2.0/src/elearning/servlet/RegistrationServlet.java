package elearning.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coreservlets.ServletUtilities;

import java.util.regex.Pattern;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		String error = null;
		String email = ServletUtilities.filter(request.getParameter("email"));
		if (null==email || !Pattern.matches(".+@.+\\..{2,}", email)){
			error = "Please, provide valid e-mail<br/>";
		}
		String password = ServletUtilities.filter(request.getParameter("password"));
		if (null==password || !Pattern.matches(".{6,}", password)){
			error += "Please, provide valid password<br/>";
		}
		String[] courses = ServletUtilities.filter(request.getParameterValues("courses"));
		if (null==courses || courses.length==0){
			error += "Please, select courses to study<br/>";
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Welcome</title></head><body>");
		if(null==error){
			out.println("<h1>Welcome, " + email + "</h1>");
			out.println("<h2>Thank you for choosing the following courses:</h2><ol>");
			for(String course: courses)
				out.println("<li>" + course + "</li>");
			out.println("</ol>");
		} else {
			out.println("<div style='color:red'>");
			out.println(error);
			out.println("</div>");
			out.println("<a href='registration.html'>Go to registration page</a>");
		}
		out.println("</body></html>");		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
