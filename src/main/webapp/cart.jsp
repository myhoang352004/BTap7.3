<%-- 
    Document   : cart
    Created on : Oct 2, 2024, 8:07:51 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
       <title>Murach's Java Servlet</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="main.css" type="text/css"/>
    </head>
    <body>
        <h1>Your Cart</h1>
        <table>
            <tr>
                <th>Quantity</th>
                <th>Description</th>
                <th>Price</th>
                <th>Amount</th>
                <th></th>               
            </tr>
            <c:forEach var="total" items="${cart.totals}">
            <tr>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="productCode" value="update">
                        <input type="hidden" name="id" value="${total.item.id}"><!-- comment -->
                        <input type="text" name="quantity" value="${total.quantity}"><!-- comment -->
                        <input class="submit" type="submit" value="Update">                               
                    </form>
                </td>
                <td>${total.item.name}</td>
                <td>${total.item.price}</td>
                <td>${total.finalPrice()}</td>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="productCode" value="remove">
                        <input type="hidden" name="id" value="${total.item.id}"><!-- comment -->           
                        <input class="submit" type="submit" value="Remove">                               
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
        <p><b>To change the quantity</b>, enter the new quantity and click on Update button</p>
        <form action="" method="post">
            <input type="hidden" name="productCode" value="join">      
            <input type="submit" value="Continue Shopping" id="go">                               
        </form><br>
        <form action="" method="post">
            <input type="hidden" name="productCode" value="checkout">      
            <input type="submit" value="Checkout" id="checkout">                               
        </form>
    </body>
</html>
