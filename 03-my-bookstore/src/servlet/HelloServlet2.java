package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet2
 */
public class HelloServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String bgcolor = "white";
	private String color = "black";
	

	@Override
	public void init() throws ServletException {
		bgcolor = getServletConfig().getInitParameter("bgcolor");
		color = getServletContext().getInitParameter("color");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World!</title>");
        out.println("</head>");
        out.printf("<body bgcolor='%s' style='color:%s'>\n", bgcolor, color);
        out.println("<h1>Hello World!</h1>");
        out.append("Hello from HelloServlet2!<br>").append(request.getRequestURI());
        out.println("</body>");
        out.println("</html>");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
