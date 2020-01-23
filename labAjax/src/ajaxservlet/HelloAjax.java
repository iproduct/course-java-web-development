package ajaxservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloAjax
 */
@WebServlet(
		urlPatterns = { "/HelloAjax" }, 
		initParams = { 
				@WebInitParam(name = "message", value = "Hello from Ajax")
		})
public class HelloAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String helloMessage = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloAjax() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	helloMessage = getInitParameter("message");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		if(name==null || name.trim().length() == 0)
			name = "Anonimous";
		//TODO: filter input data
		out.println(helloMessage + ", " + name + "!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	 */
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
