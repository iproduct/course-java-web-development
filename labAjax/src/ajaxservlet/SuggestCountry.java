package ajaxservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static ajaxservlet.util.CountryCapitals.pairs;

/**
 * Servlet implementation class SuggestCountry
 */
@WebServlet("/SuggestCountry")
public class SuggestCountry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Allow CORS
		addAccessControlHeaders(request, response);
		String country = request.getParameter("country");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		if(country == null || country.trim().length()==0){
			out.print("[]");
			return;
		}
		out.print("[");
		boolean firstResult = true;
		for(String[] elem: pairs){
			if(elem[0].startsWith(country.toUpperCase())){
				if(!firstResult)
					out.print(",");
				firstResult = false;
				out.print("{\"country\":\"" + elem[0] 
					+ "\",\"capital\":\"" + elem[1]
					+ "\"}");
			}
		}
		out.print("]");		
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
		//Allow CORS
		addAccessControlHeaders(request, response);
	}

	protected void addAccessControlHeaders(
			HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String origin = request.getHeader("Origin");
		if(origin != null && origin.length() > 0){
			response.setHeader("Access-Control-Allow-Origin", origin);
		}
		String headers = request.getHeader("Access-Control-Request-Headers");
		if(headers != null && headers.length() > 0){
			response.setHeader("Access-Control-Allow-Headers", headers);
		}
		String method = request.getHeader("Access-Control-Request-Method");
		if(method != null && method.length() > 0){
			response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS," 
					+ method);
		}
		response.setIntHeader("Access-Control-Max-Age", 864000);
	}
	
}
