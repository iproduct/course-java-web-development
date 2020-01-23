<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
  <title>IPT Bookstore</title>
  <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body bgcolor="#33CCFF">
  <font face="Times New Roman,Times" size="+3">
    Best Books Forever
  </font>
  <hr><p>
  <div class="center">
  <form name="shoppingForm" action="ShoppingServlet" method="POST">
  <b>Choose a Book:</b> 
  <jsp:useBean id="bookController" scope="application" 
  		class="business.BookDBController"></jsp:useBean>
  <select name="bookId">
  	<c:set var="books" value="${applicationScope.bookController.allBooks}" />
  	<c:forEach var="book" items="${books}" varStatus="status">
  		<option value="${book.id}">
    		${status.index}: ${book.title} - ${book.price}
    	</option>
  	</c:forEach>
  </select>
  <b>Quantity: </b><input type="text" name="quantity" SIZE="3" value=1>
  <input type="hidden" name="action" value="ADD">
  <input type="submit" name="Submit" value="Add to Cart">
  </form>
  </div>
  <%@ include file="Cart.jsp" %>
</body>
</html>