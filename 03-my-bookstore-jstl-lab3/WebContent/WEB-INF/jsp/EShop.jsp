<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
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
  <jsp:useBean id="bookController" scope="application" 
  		class="bookstore.dao.BookDBController"></jsp:useBean>
  <select name="bookId">
  	<%List<Book> books = 
  	((BookDBController)getServletContext().getAttribute("bookController"))
  		.getAllBooks();
  	for(Book b: books) {%>
    <option value="<%= b.getId() %>">
    	<%=b.getTitle() + " - " + b.getPrice() %>
    </option>
    <%} %>
  </select>
  <b>Quantity: </b><input type="text" name="quantity" SIZE="3" value=1>
  <input type="hidden" name="action" value="ADD">
  <input type="submit" name="Submit" value="Add to Cart">
  </form>
  <p>
  <jsp:include page="Cart.jsp" flush="true" />
</body>
</html>