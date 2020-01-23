<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 <c:set var="cartBooks" value="${sessionScope['shopping.shoppingcart']}" />
 <c:if test="${fn:length(cartBooks)> 0}">
  <table class="checkout">
    <tr>
      <th><b>TITLE</b></th>
      <th><b>AUTHOR</b></th>
      <th><b>YEAR</b></th>
      <th><b>PRICE</b></th>
      <th><b>QUANTITY</b></th>
      <th> </th>
    </tr>
    <c:forEach var="cb" items="${cartBooks}" >
	  <tr>
	      <td><b>${cb.book.title}</b></td>
	      <td><b>${cb.book.authors}</b></td>
	      <td><b>${cb.book.publishedDate}</b></td>
	      <td><b>${cb.book.price}</b></td>
	      <td><b>${cb.quantity}</b></td>
          <td>
	         <form name="deleteForm" action="ShoppingServlet"  method="POST">
		          <input type="submit" value="Delete">
		          <input type="hidden" name= "bookId" value='${book.id}'>
		          <input type="hidden" name="action" value="DELETE">
	         </form> 
        </td>
      </tr> 
    </c:forEach>
  </table>
  <form name="checkoutForm" action="ShoppingServlet" method="POST" class="action-form">
    <input type="hidden" name="action" value="CHECKOUT">
    <input type="submit" name="Checkout" value="Checkout" class="center">
  </form>
 </c:if>

