package coreservlets;

import javax.servlet.*;
import javax.servlet.http.*;

/** Some simple timesavers. Note that most are static methods.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class ServletUtilities {
  public static final String DOCTYPE =
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
    "Transitional//EN\">";

  public static String headWithTitle(String title) {
    return(DOCTYPE + "\n" +
           "<HTML>\n" +
           "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n");
  }

  /** Read a parameter with the specified name, convert it
   *  to an int, and return it. Return the designated default
   *  value if the parameter doesn't exist or if it is an
   *  illegal integer format.
  */
  
  public static int getIntParameter(HttpServletRequest request,
                                    String paramName,
                                    int defaultValue) {
    String paramString = request.getParameter(paramName);
    int paramValue;
    try {
      paramValue = Integer.parseInt(paramString);
    } catch(NumberFormatException nfe) { // null or bad format
      paramValue = defaultValue;
    }
    return(paramValue);
  }

  public static double getDoubleParameter
                                 (HttpServletRequest request,
                                  String paramName,
                                  double defaultValue) {
    String paramString = request.getParameter(paramName);
    double paramValue;
    try {
      paramValue = Double.parseDouble(paramString);
    } catch(NumberFormatException nfe) { // null or bad format
      paramValue = defaultValue;
    }
    return(paramValue);
  }

  /** Replaces characters that have special HTML meanings
   *  with their corresponding HTML character entities.
   */
  
  // Note that Javadoc is not used for the more detailed
  // documentation due to the difficulty of making the
  // special chars readable in both plain text and HTML.
  //
  // Given a string, this method replaces all occurrences of
  //  '<' with '&lt;', all occurrences of '>' with
  //  '&gt;', and (to handle cases that occur inside attribute
  //  values), all occurrences of double quotes with
  //  '&quot;' and all occurrences of '&' with '&amp;'.
  //  Without such filtering, an arbitrary string
  //  could not safely be inserted in a Web page.

  public static String filter(String input) {
	    if (!hasSpecialChars(input)) {
	      return(input);
	    }
	    StringBuffer filtered = new StringBuffer(input.length());
	    char c;
	    for(int i=0; i<input.length(); i++) {
	      c = input.charAt(i);
	      switch(c) {
	        case '<': filtered.append("&lt;"); break;
	        case '>': filtered.append("&gt;"); break;
	        case '"': filtered.append("&quot;"); break;
	        case '&': filtered.append("&amp;"); break;
	        default: filtered.append(c);
	      }
	    }
	    return(filtered.toString());
	  }

  public static String[] filter(String[] inputs) {
	   if ((inputs != null) && (inputs.length > 0)) {
		   String[] response = new String[inputs.length];
		   for(int i = 0; i < inputs.length; i++){
			   response[i] = filter(inputs[i]);
		   }
		   return response;
	   } else {
		   return new String[0];
	   }
  }

  private static boolean hasSpecialChars(String input) {
    boolean flag = false;
    if ((input != null) && (input.length() > 0)) {
      char c;
      for(int i=0; i<input.length(); i++) {
        c = input.charAt(i);
        switch(c) {
          case '<': flag = true; break;
          case '>': flag = true; break;
          case '"': flag = true; break;
          case '&': flag = true; break;
        }
      }
    }
    return(flag);
  }
}
