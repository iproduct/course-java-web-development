<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
  <title>IPT Bookstore Checkout</title>
  <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body bgcolor="#33CCFF">
  <font face="Times New Roman,Times" size=+3>
   	IPT  Bookstore Checkout
  </font>
  <hr><p>
  <table class="checkout">
  <tr>
    <td><b>TITLE</b></td>
    <td><b>AUTHOR</b></td>
    <td><b>YEAR</b></td>
    <td><b>PRICE</b></td>
    <td><b>QUANTITY</b></td>
  </tr>

  <c:set var="purchasedBooks" value="${sessionScope['shopping.shoppingcart']}" />
  <c:forEach var="cb" items="${purchasedBooks}" >
	  <tr>
	      <td><b>${cb.book.title}</b></td>
	      <td><b>${cb.book.authors}</b></td>
	      <td><b>${cb.book.publishedDate}</b></td>
	      <td><b>${cb.book.price}</b></td>
	      <td><b>${cb.quantity}</b></td>
	  </tr>
  </c:forEach>

    <tr>
    <td> </td>
    <td> </td>
    <td><b>TOTAL</b></td>
    <td><b>$${requestScope.amount}</b></td>
    <td> </td>
  </tr>
  </table>
  <p>
  <a href="ShoppingServlet">Shop some more!</a>
</body>
</html>
