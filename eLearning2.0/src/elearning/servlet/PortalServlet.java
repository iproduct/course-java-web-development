package elearning.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import elearning.controller.UserController;
import elearning.entity.User;
import elearning.exceptions.UserException;
import static coreservlets.ServletUtilities.*;

@WebServlet("/portal")
public class PortalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HOME = "home";
	public static final String LOGIN = "login";
	public static final String LOGOUT = "logout";
	public static final String REGISTER = "register";
	public static final String COURSES_SELECT = "select";
	public static final String COURSES_CHECKOUT = "checkout";
	
	private UserController userController = new UserController();
       
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = filter(request.getParameter("action"));
		if (action == null) {
			action = HOME;
		}
		RequestDispatcher rd = null;
		
		HttpSession session = request.getSession(true);
		String currentUser = (String) session.getAttribute("currentUser");
		if(currentUser == null){
			if(REGISTER.equals(action)){ 
				action = processActionRegister(request);
			} else {
				String email = filter(request.getParameter("email"));
				String password = filter(request.getParameter("password"));
				if((email == null || email.length() == 0) && (password == null || password.length() == 0)){
					rd = request.getRequestDispatcher("/WEB-INF/view/LoginServlet");
				} else if((email != null && email.length() > 0) || (password != null || password.length() > 0)){
					if(isValidUser(email, password)){ //correct
						session.setAttribute("currentUser", email);
						action = HOME;
					} else { //not empty but not correct
						request.setAttribute("errors", "Error: Invalid e-mail or password");
						request.setAttribute("email", email);
						action = LOGIN;		
					}
				} else { // empty fields
					action = LOGIN;
				}
			}
		}
		if(rd == null) {
			switch (action) {
			case REGISTER: 
				rd = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp"); break;
			case COURSES_SELECT: 
				rd = request.getRequestDispatcher("/WEB-INF/view/CoursesSelectServlet"); break;
			case COURSES_CHECKOUT: 
				rd = request.getRequestDispatcher("/WEB-INF/view/CoursesCheckoutServlet"); break;
			case LOGOUT: 
				session.invalidate();
				action = HOME;
				rd = request.getRequestDispatcher("/WEB-INF/view/LoginServlet");
				break;
			case HOME: 
				rd = request.getRequestDispatcher("/WEB-INF/view/HomeServlet"); break;
			case LOGIN: 
			default:
				rd = request.getRequestDispatcher("/WEB-INF/view/LoginServlet");
			}
		}
		
		request.setAttribute("action", action);
		rd.forward(request, response);
	}

	private String processActionRegister(HttpServletRequest request) {
		String errors = "";
		String fname = request.getParameter("fname");
		if(fname== null || fname.length() == 0){
			errors += "First name is required field.<br />";
		}
		String lname = request.getParameter("lname");
		if(lname== null || lname.length() == 0){
			errors += "Last name is required field.<br />";
		}
		String nickname = request.getParameter("nickname");
		if(nickname == null || nickname.length() == 0){
			errors += "Nickname is required field.<br />";
		}
		String email = request.getParameter("email");
		if(email== null || email.length() == 0){
			errors += "E-mail name is required field.<br />";
		} else if(!Pattern.matches(".+@.+\\.\\w{2,}", email)){
			errors += "E-mail name is invalid.<br />";
		}
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		if(password == null || password.length() < 6 || password == null || password.length() < 6 
				|| !password.equals(password2)){
			errors += "Passwords do not match or are less then 6 characters.<br />";
		}
		String ageStr = request.getParameter("age");
		int age = 0;
		if(ageStr!= null && ageStr.length() > 0){
			try{
				age = Integer.parseInt(ageStr);
			} catch(NumberFormatException e){
				errors += "Age value is invalid.<br />";
			}
			if(age < 0)
				errors += "Age value is invalid.<br />";
		}
		
		if(errors.length() == 0){
			synchronized(userController){
				try {
					userController.addUser(new User(fname, lname, nickname, email, password, age));
				} catch (UserException e) {
					errors += e.getMessage();
					e.printStackTrace();
				}
			}
		}
		if(errors.length() == 0){
			request.getSession().setAttribute("currentUser", email);
			return HOME;
		}
		
		if(fname==null && lname==null && nickname==null && email==null && password==null
				&& password2==null && age==0)
			errors = "";
		request.setAttribute("errors", errors);
		return REGISTER;
	}

	private boolean isValidUser(String email, String password) {
		return Pattern.matches(".+@.+\\.\\w{2,}", email) && Pattern.matches(".{6,}", password);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
