<%@page session="true" import="java.util.*, bookstore.model.Book" %>
<%
List<Book> cart = (List<Book>)session.getAttribute("shopping.shoppingcart");
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
            Book b = (Book) cart.get(index);
    %>
    <tr>
      <td><b><%= b.getTitle() %></b></td>
      <td><b><%= b.getAuthors() %></b></td>
      <td><b><%= b.getPublishedDate() %></b></td>
      <td><b><%= b.getPrice() %></b></td>
      <td><b><%= b.getQuantity() %></b></td>
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

