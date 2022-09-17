<%-- 
    Document   : onlShopping
    Created on : Jun 22, 2022, 12:38:38 AM
    Author     : Admin
--%>

<%--<%@page import="java.util.LinkedList"%>
<%@page import="thuongng.tblProduct.TblProductDTO"%>
<%@page import="java.util.List"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USERNAME}">
            <font color="red">
            Welcome, ${sessionScope.FULLNAME} 
            </font>
            <form action = "logout">
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </c:if>
        <form action="showAllProduct">
            <input type="submit" value="Show All Product" name="btAction"/>
        </form>
        <c:set var="products" value="${requestScope.SHOW_RESULT}" />
        <c:if test="${not empty products}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Add To Cart</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                    <form action="addBook">
                        <tr>
                            <td>
                                ${product.productID}
                                <input type="hidden" name="productID" value="${product.productID}" />
                            </td>
                            <td>
                                ${product.productName}
                                <input type="hidden" name="productName" value="${product.price}" />
                            </td>
                            <td>
                                ${product.price}
                                <input type="hidden" name="price" value="${product.price}" />
                            </td>
                            <td>
                                ${product.description}
                                <input type="hidden" name="description" value="${product.description}" />
                            </td>
                            <td>
                                <input type="submit" value="Add To Cart" name="btAction" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
            <form action="viewProduct">
                <tr>
                <input type="submit" value="Show Your Cart" name="btAction" />
                </tr>
            </form>    
        </table>
            <a href="loginPage">Back To Login Page</a>
    </c:if>
    <%--   <% 
         List<TblProductDTO> products = (List<TblProductDTO>) request.getAttribute("SHOW_RESULT");
         if(products != null){
             %>  
             <table border="1">
                 <thead>
                     <tr>
                         <th>ID</th>
                         <th>Name</th>
                         <th>Price</th>
                         <th>Description</th>
                         <th>Add To Cart</th>
                     </tr>
                 </thead>
                 <tbody>
                     <% 
                        for(TblProductDTO product : products){
                            %>
                 <form action="DispatchController">
                         <tr>
                             <td>
                                 <%= product.getProductID() %>
                                   <input type="hidden" name="productID" value="<%= product.getProductID()%>" />
                             </td>
                             <td>
                                  <%= product.getProductName() %>
                                  <input type="hidden" name="productName" value="<%= product.getProductName()%>" />
                             </td>
                             <td>
                                 <%= product.getPrice() %>
                                 <input type="hidden" name="price" value="<%= product.getPrice()%>" />
                             </td>
                             <td>
                                 <%= product.getDescription() %>
                                  <input type="hidden" name="description" value="<%= product.getDescription() %>" />
                             </td>
                             <td>
                                 <input type="submit" value="Add To Cart" name="btAction" />
                             </td>
                     </tr>
                  </form>
                     <%
                        }
                     %>
                        <form action="DispatchController">
                            <tr>
                                <input type="submit" value="ShowYourCart" name="btAction" />
                            </tr>
                        </form>        
                 </tbody>
             </table>

        <%
          }
        
        %> --%>
</body>
</html>
