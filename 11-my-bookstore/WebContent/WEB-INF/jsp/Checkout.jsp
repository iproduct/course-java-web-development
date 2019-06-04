<%@ page session="true" import="java.util.*, bookstore.model.*" %>
<html>
<head>
  <title>Music Without Borders Checkout</title>
</head>
<body bgcolor="#33CCFF">
  <font face="Times New Roman,Times" size=+3>
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
  <%
  List<CartBean> cart = (List<CartBean>) session.getAttribute("shopping.shoppingcart");
  if (cart != null && (cart.size() >0)) {
  String amount = (String) request.getAttribute("amount");
  for (int index=0; index< cart.size();index++) {
      CartBean cb = (CartBean) cart.get(index);
      Book b = cb.getBook();
  %>
  <tr>
      <td><b><%= b.getTitle() %></b></td>
      <td><b><%= b.getAuthors() %></b></td>
      <td><b><%= b.getPublishedDate() %></b></td>
      <td><b><%= b.getPrice() %></b></td>
      <td><b><%= cb.getQuantity() %></b></td>
  </tr>
  <%
    }
  %>
    <tr>
    <td>     </td>
    <td>     </td>
    <td><b>TOTAL</b></td>
    <td><b>$<%= amount %></b></td>
    <td>     </td>
  </tr>
  <%
    session.invalidate();
  }
  %>
  </table>
  <p>
  <a href="ShoppingServlet">Shop some more!</a>
</body>
</html>
