<%@page session="true" import="java.util.*, bookstore.model.*" %>
<%
List<CartBean> cart = (List<CartBean>)session.getAttribute("shopping.shoppingcart");
if (cart != null && (cart.size() >0)) {
%>
  <center>
  <table border="0" cellpadding="0" width="100%" bgcolor="#FFFFFF">
    <tr>
      <td><b>TITLE</b></td>
      <td><b>AUTHOR</b></td>
      <td><b>YEAR</b></td>
      <td><b>PRICE</b></td>
      <td><b>QUANTITY</b></td>
      <td></td>
    </tr>
    <%
    	for (int index=0; index< cart.size();index++) {
    		CartBean cb = cart.get(index);
            Book b = cb.getBook();
    %>
    <tr>
      <td><b><%= b.getTitle() %></b></td>
      <td><b><%= b.getAuthors() %></b></td>
      <td><b><%= b.getPublishedDate().toString() %></b></td>
      <td><b><%= b.getPrice() %></b></td>
      <td><b><%= cb.getQuantity() %></b></td>
      <td>
        <form name="deleteForm"
              action="ShoppingServlet"
              method="POST">
          <input type="submit" value="Delete">
          <input type="hidden" name= "bookId" value='<%= b.getId() %>'>
          <input type="hidden" name="action"
           value="DELETE">
         </form> 
      </td>
    </tr> 
    <% } %>
  </table>
  <p>
  <form name="checkoutForm"
    action="ShoppingServlet"
    method="POST">
    <input type="hidden" name="action" value="CHECKOUT">
    <input type="submit" name="Checkout" value="Checkout">
  </form>
  </center>
<% } %>

