package elearning.servlet;

import static coreservlets.ServletUtilities.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxValidatorServlet
 */
@WebServlet(
	urlPatterns = { "/AjaxValidatorServlet" }, 
	initParams = { 
		@WebInitParam(name = "emailValidationTemplate", value = ".+@.+\\.\\w{2,}")
	})
public class AjaxValidatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected String emailTemplate;
	public static final String[] ALLOWED_DOMAINS = {
		"http://localhost:8080", 
//		"http://127.0.0.1:8080"
	};
	
	@Override
	public void init() throws ServletException {
		emailTemplate = getInitParameter("emailValidationTemplate");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		allowCORS(request, response);
		String result;
		String email = filter(request.getParameter("email"));
		if(null==email || email.length() == 0)
			result = "Error: Email can not be null";
		else if(Pattern.matches(emailTemplate, email))
			result = "valid";
		else
			result = "Error: Invalid email";
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		allowCORS(req, resp);
		super.doOptions(req, resp);
	}

	private void allowCORS(HttpServletRequest req, HttpServletResponse resp) {
		String originHeader = req.getHeader("Origin");
		System.out.println("!!! Origin = " + originHeader);
		String headerHeader = req.getHeader("Access-Control-Request-Headers");
		System.out.println("!!! Access-Control-Request-Headers = " + headerHeader);
		
		List<String> acl = Arrays.asList(ALLOWED_DOMAINS);
		if(originHeader != null && originHeader.length() > 0
				&& acl.contains(originHeader)){
			resp.setHeader("Access-Control-Allow-Origin", originHeader);
			resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
			resp.setIntHeader("Access-Control-Max-Age", 1728000);
			if(headerHeader != null && headerHeader.length() > 0){
				resp.setHeader("Access-Control-Allow-Headers", headerHeader);
			}
		}
	}
	
}
