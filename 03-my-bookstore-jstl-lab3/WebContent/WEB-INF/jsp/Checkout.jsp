<%@ page session="true" import="java.util.*, bookstore.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html>
<head>
  <title>Music Without Borders Checkout</title>
</head>
<body bgcolor="#33CCFF">
  <font face="Times New Roman,Times" size="+3">
   	IPT  Bookstore Checkout
  </font>
  <hr><p>
  <table border="0" cellpadding="0" width="100%" bgcolor="#FFFFFF">
  <tr>
    <td><b>TITLE</b></td>
    <td><b>AUTHOR</b></td>
    <td><b>YEAR</b></td>
    <td><b>PRICE</b></td>
    <td><b>QUANTITY</b></td>
    <td></td>
  </tr>
  <c:forEach var="cb" items="${sessionScope.shoppingcart}">
	  <tr>
	      <td><b><c:out value="${cb.book.title}" /></b></td>
	      <td><b><c:out value="${cb.book.authors}" /></b></td>
	      <td><b><c:out value="${cb.book.publishDate}" /></b></td>
	      <td><b><c:out value="${cb.book.price}" /></td>
	      <td><b><c:out value="${cb.quantity}" /></b></td>
	  </tr>
  </c:forEach>
  <tr>
    <td>     </td>
    <td>     </td>
    <td><b>TOTAL</b></td>
    <td><b>${requestScope.amount}</b></td>
    <td>     </td>
  </tr>
  </table>
  <p>
  <a href="ShoppingServlet">Shop some more!</a>
</body>
</html>
