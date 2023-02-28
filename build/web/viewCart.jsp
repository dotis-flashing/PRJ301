<%-- 
    Document   : viewCart
    Created on : Dec 7, 2022, 12:48:38 PM
    Author     : Admin
--%>

<%@page import="sample.product.ProductDTO"%>
<%@page import="sample.product.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
        <link rel="stylesheet" type="text/css" href="shoppingStyles.css" media="screen" />
    </head>
    <header>
        <form action="MainController" method="POST">
        <input type="submit" name="action" value="View" />
        </form>
        <form action="ShoppingController" method="POST">
        <input type="submit" value="Shop" />
        </form>
        <a href="MainController?action=Logout">Logout</a>
    </header>
    <body>
        <h1>Your selected items</h1>
        <form action="MainController" method="POST">
        <%
            String message = (String)request.getAttribute("MESSAGE");
            if (message == null) message = "";
            
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
                if (cart.getCart().size() > 0) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Edit</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    double total = 0;
                    for (ProductDTO p : cart.getCart().values()) {
                        total += p.getPrice() * p.getQuantity();
                %>
            <form action="MainController" method="POST">
                <tr>
                    <td><%= count++%></td>
                    <td><%= p.getId()%>
                        <input type="hidden" name="id" value="<%= p.getId()%>"/>
                    </td>
                    <td><%= p.getName()%>

                    </td>
                    <td><%= p.getPrice()%>$

                    </td>
                    <td>
                        <input type="number" name="quantity" value="<%= p.getQuantity()%>" min="1"/>
                    </td>
                    <td><%= p.getPrice() * p.getQuantity()%>$</td>
                    <!--update here-->
                    <td>
                        <input type="submit" name="action" value="Edit"/>
                    </td>
                    <td>
                        <input type="submit" name="action" value="Remove"/>
                    </td>
                </tr>
            </form>


            <%
                }
            %>

        </tbody>
    </table>
    <h1>Total: <%= total%>$</h1>
    
    <%
            }
%>
    
<%
        }
    %>
   <input type="submit" name="action" value="Checkout"/>
    <a><%= message%></a>
</body>
</html>
