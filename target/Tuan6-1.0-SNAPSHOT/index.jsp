<%-- 
    Document   : index
    Created on : Oct 2, 2024, 4:11:46 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Murach's Java Servlet</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="main.css" type="text/css"/>
    <body>
        <h1>CD List</h1>
        <table>
            <tr>
                <th>Description</th>
                <th>Price</th>
                <th></th>
            </tr>
            <tr>
                <td>86 (the band) - True Life Songs and Pictures</td>
                <td>$14.95</td>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="productCode" value="add">
                        <input type="hidden" name="id" value="8601">
                        <input type="hidden" name="name" value="86 (the band) - True Life Songs and Pictures">
                        <input type="hidden" name="price" value="14.95"><!-- comment -->
                        <input class="submit" type="submit" value="Add to Cart">
                    </form>
                </td>
            </tr>
            <tr>
                <td>Paddlefoot - The first CD</td>
                <td>$12.95</td>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="productCode" value="add">
                        <input type="hidden" name="id" value="pdf1">
                        <input type="hidden" name="name" value="Paddlefoot - The first CD">
                        <input type="hidden" name="price" value="12.95"><!-- comment -->
                        <input class="submit" type="submit" value="Add to Cart">
                    </form>
                </td>
            </tr>
            <tr>
                <td>Paddlefoot - The second CD</td>
                <td>$14.95</td>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="productCode" value="add">
                        <input type="hidden" name="id" value="pdf2">
                        <input type="hidden" name="name" value="Paddlefoot - The second CD">
                        <input type="hidden" name="price" value="14.95"><!-- comment -->
                        <input class="submit" type="submit" value="Add to Cart">
                    </form>
                </td>
            </tr>
            <tr>
                <td>Joe Rut - Genuine Wood Grained Finish</td>
                <td>$14.95</td>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="productCode" value="add">
                        <input type="hidden" name="id" value="jrt">
                        <input type="hidden" name="name" value="Joe Rut - Genuine Wood Grained Finish">
                        <input type="hidden" name="price" value="14.95"><!-- comment -->
                        <input class="submit" type="submit" value="Add to Cart">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
