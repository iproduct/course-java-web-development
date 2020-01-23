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
@WebServlet("/WEB-INF/view/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		String errors = (String) request.getAttribute("errors");
		String email = (String) request.getAttribute("email");
		if(email == null) email = "";
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Welcome</title></head><body>");

		out.println("<h1>Please enter yor data:</h1>");
		out.println("E-mail: ");
		out.println("<form method='POST'>");
		out.println("<input type='text' id='email' name='email' value='" + email
				+ "' placeholder='youremail@somedomain.com'/><br />");
		out.println("Password: ");
		out.println("<input type='password' id='password' name='password' /><br />");
		out.println("<input type='submit' id='submit' name='submit' value='Login' />");
		out.println("<input type='reset' id='reset' name='reset' value='Reset' /><br />");
		if(errors != null){
			out.println("<div style='color:red'>");
			out.println(errors);
			out.println("</div>");
		}
		String courseSelectUrl = response.encodeURL(request.getContextPath()+"/portal?action=" 
				+ PortalServlet.REGISTER);
		out.println("<a href='" + courseSelectUrl + "'>Go to registration page</a><br />");
		
		out.println("</form></body></html>");		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
