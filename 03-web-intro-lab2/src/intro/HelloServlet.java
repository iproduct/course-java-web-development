package intro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = { "/hello/*" }, initParams = {
		@WebInitParam(name = "helloText", value = "Hi"), @WebInitParam(name = "color", value = "red") })
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;

//	/**
//	 * @see Servlet#init(ServletConfig)
//	 */
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		this.config = config;
//	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String appName = getServletContext().getInitParameter("appName");
		String bgColor = getServletContext().getInitParameter("bgColor");
		String color = getServletConfig().getInitParameter("color");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Request Information Example</title>");
		out.println("</head>");
		out.printf("<body bgcolor='%s' style='color:%s'>", bgColor, color, appName);
		out.println("<h1>" + appName + "</h1>");
		out.println("Method: " + request.getMethod() + "<br>");
		out.println("Request URI: " + request.getRequestURI() + "<br>");
		out.println("Protocol: " + request.getProtocol() + "<br>");
		out.println("Context path: " + request.getContextPath() + "<br>");
		out.println("Servlet path: " + request.getServletPath() + "<br>");
		out.println("PathInfo: " + request.getPathInfo() + "<br>");
		out.println("Remote Address: " + request.getRemoteAddr() + "<br>");
		Enumeration e = request.getHeaderNames();
		out.println("<table>");
		while (e.hasMoreElements()) {
			out.println("<tr><td>");
			String name = (String) e.nextElement();
			out.print(name);
			out.print("</td><td>");
			String value = request.getHeader(name);
			out.print(value);
			out.println("</td></tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
