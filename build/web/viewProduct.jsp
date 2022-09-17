<%-- 
    Document   : viewProduct
    Created on : Jun 25, 2022, 5:04:14 PM
    Author     : Admin
--%>

<%--<%@page import="thuongng.cart.CartObjectV2"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="thuongng.cart.CartObjectV2List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USERNAME}">
            <h1>You Must Login To Continue</h1>
            <form action="login" method="POST">
                Username <input type="text" name="txtUsername" value="" /><br/>
                Password <input type="password" name="txtPassword" value="" /><br/>
                <input type="submit" value="Continue" name="btnAction" />
                <input type="reset" value="Reset"/>
            </form>
        </c:if>
        <c:if test="${not empty sessionScope.USERNAME}" >
            <font color="red">
            Welcome, ${sessionScope.FULLNAME} 
            </font>
            <form action = "logout">
                <input type="submit" value="Logout" name="btAction" />
            </form>
            <h1>Your Cart include</h1>
            <c:if test="${not empty sessionScope}" >
                <c:set var="cart" value="${sessionScope.CARTLIST}"/>
                <c:if test="${not empty cart}">
                    <c:set var="items" value="${cart.list}"/>
                    <c:if test="${not empty items}">
                        <form action="deleteBookFromCart">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Description</th>
                                        <th>Quantity</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${items}" varStatus="counter">
                                        <tr>
                                            <td>
                                                ${counter.count}
                                            </td>
                                            <td>
                                                ${item.product.productID}
                                            </td>
                                            <td>
                                                ${item.product.productName}
                                            </td>
                                            <td>
                                                ${item.product.price}
                                            </td>
                                            <td>
                                                ${item.product.description}
                                            </td>
                                            <td>
                                                ${item.quantity}
                                            </td> 
                                            <td>
                                                <input type="checkbox" name="checkedItem" value= "${item.product.productID}" />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="3">
                                           <a href="onlShopping.jsp">Add More Items</a> 
                                        </td>
                                        <td colspan="4">
                                            <input type="submit" value="Delete Selected Book" name="btAction" />
                                        </td> 
                                    </tr>
                                </tbody>
                            </table>
                            <h2> Total : <bold>${cart.totalMoney}</bold></h2>
                        </form>
                        <form action="checkOut">
                            <tr>
                                <td>
                                    <input type="submit" value="CheckOut" name="btnAction" /> 
                                </td>
                            </tr>
                        </form>
                    </c:if>
                    <c:if test="${empty items }">
                        <h2>
                            No cart has existed
                        </h2>
                        <a href="onlShopping.jsp">Add More Items</a> 
                    </c:if>
                </c:if>
                <c:if test="${empty cart}">
                    <h2>
                        No cart has existed
                    </h2>
                    <a href="onlShopping.jsp">Add More Items</a> 
                </c:if>
            </c:if>
            <c:if test="${empty sessionScope}">
                <h2>
                    No cart has existed
                </h2>
                <a href="onlShopping.jsp">Add More Items</a> 
            </c:if>
        </c:if>

        <%--  <% 
          //1 cus goes to his/her cart place
          if(session != null){
              //2 cus goes to take his /her cart
             CartObjectV2List cart = (CartObjectV2List)session.getAttribute("CARTLIST");
             if(cart != null){
                 //3 cus takes items from cart
                 ArrayList<CartObjectV2> items = cart.getList();
                 if(items != null){
                     //4 .show items
                       %>
                       <form action="DispatchController">
                       <table border="1">
                           <thead>
                               <tr>
                                   <th>No.</th>
                                   <th>ID</th>
                                   <th>Name</th>
                                   <th>Price</th>
                                   <th>Description</th>
                                   <th>Quantity</th>
                                   <th>Action</th>
                               </tr>
                           </thead>
                           <tbody>
                              <% 
                                int count = 0;
                                for(CartObjectV2 item : items){
                                    %>  
                                 <tr>
                                   <td>
                                       <%=++count %>
                                   </td>
                                   <td>
                                       <%= item.getProduct().getProductID()  %>
                                   </td>
                                   <td>
                                       <%= item.getProduct().getProductName() %>
                                   </td>
                                   <td>
                                       <%= item.getProduct().getPrice() %>
                                   </td>
                                   <td>
                                        <%= item.getProduct().getDescription()%>
                                   </td>
                                   <td>
                                        <%= item.getQuantity() %>
                                   </td>
                                   <td>
                                       <input type="checkbox" name="checkedItem" value= "<%= item.getProduct().getProductID() %>" />
                                   </td>
                               </tr>
                               <%
                                }//end get each item from items
                              %> 
                              <tr>
                                  <td colspan="3">
                                    <a href="onlShopping.jsp">Add More Items</a>
                                  </td>
                                  <td colspan="4">
                                      <input type="submit" value="Delete Selected Book" name="btAction" />
                                  </td> 
                              </tr>
                              <tr>
                                  <td>
                                      <input type="submit" value="CheckOut" name="btAction" /> 
                                  </td>
                            </tr>
                           </tbody>
                       </table>
                       </form>
                              <h2>Total : <%= cart.getAllPrice() %></h2>
         <%
                     
                     return;
                 }// items has existed
             }// cart has existed
          }//session has existed
        %>
        <h2>
            No cart has existed
        </h2>
         <a href="onlShopping.jsp">Add More Items</a> --%>
    </body>
</html>
