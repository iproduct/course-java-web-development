<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<html>
<head>
  <title>Best Books Forever</title>
</head>
<body bgcolor="#33CCFF">
  <font face="Times New Roman,Times" size="+3">
    Best Books Forever
  </font>
  <hr><p>
  
  <form name="shoppingForm" 
    action="ShoppingServlet" 
    method="POST">
  <b>Choose a Book:</b> 
    <select name="bookId">
  	<c:set var="books" value="${applicationScope.bookController.allBooks}" />
  	<c:forEach var="book" items="${books}">
	    <option value="${book.id}">
	    	<c:out value="${book.title}" escapeXml="true"></c:out> -
	    	<c:out value="${book.price}" escapeXml="true"></c:out>
	    </option>
    </c:forEach>
   </select>
  <b>Quantity: </b><input type="text" name="quantity" SIZE="3" value=1>
  <input type="hidden" name="action" value="ADD">
  <input type="submit" name="Submit" value="Add to Cart">
  </form>
  <p>
  <c:if test="${fn:length(shoppingcart) > 0}">
  	<jsp:include page="Cart.jsp" flush="true" />
  </c:if>
</body>
</html>