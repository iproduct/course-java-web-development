package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet(
	description = "Introduction to java servlets", 
	urlPatterns = { "/hello/*" }, 
	initParams = { 
		@WebInitParam(name = "bgcolor", value = "lightblue", description = "Background color parameter")
	})
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String bgcolor = "white";
	private String color = "black";
	
	@Override
	public void init() throws ServletException {
		bgcolor = getServletConfig().getInitParameter("bgcolor");
		color = getServletContext().getInitParameter("color");
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setBufferSize(2048);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World!</title>");
        out.println("</head>");
        out.printf("<body bgcolor='%s' style='color:%s'>\n", bgcolor, color);
        out.println("<h1>Hello World!</h1>");
        out.append("Hello from HelloServlet2!<br>").append(request.getRequestURI());
        out.println("<h3>Request Information Example</h3>");
        out.println("Method: " + request.getMethod());
        out.println("Request URI: " + request.getRequestURI());
        out.println("Protocol: " + request.getProtocol());
        out.printf("Remote Address: %s<br>\n", request.getRemoteAddr());
        out.printf("Context Path: %s<br>\n", request.getContextPath());
        out.printf("Servlet Path: %s<br>\n", request.getServletPath());
        out.printf("PathInfo: %s<br>\n", request.getPathInfo());
        Map<String, String[]> params = request.getParameterMap();
        for(Map.Entry<String, String[]>  e: params.entrySet()) {
        	out.printf("%s -> %s<br>\n", e.getKey(), String.join(", ", e.getValue()));
        }
        out.printf("</body>");
        out.printf("</html>");
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
