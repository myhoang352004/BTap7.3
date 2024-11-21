<%-- 
    Document   : checkout
    Created on : Oct 2, 2024, 9:01:28 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="main.css" type="text/css"/>
        <title>CD shop</title>
    </head>
    <body>
    <center>
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
                    </form>
                </td>
                <td>${total.item.name}</td>
                <td>${total.item.price}</td>
                <td>${total.finalPrice()}</td>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="productCode" value="remove">
                        <input type="hidden" name="id" value="${total.item.id}"><!-- comment -->           
                           
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
        <h3>Tổng tiền: 
            <c:out value="${cart.totalPrice}" />
        </h3>
        </table>
        <h3>Your order has been sent to your email, please confirm</h3>        
        <h3>Thank you for coming to my CDshop</h3>
        <h4> Goodbye! Have a nice day :></h4>
    </center>
    </body>
</html>
