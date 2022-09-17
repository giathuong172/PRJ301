<%-- 
    Document   : viewCart
    Created on : Jun 20, 2022, 7:17:20 AM
    Author     : Admin
--%>

<%@page import="java.util.Map"%>
<%@page import="thuongng.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Your Cart include :</h1>
       <% 
         //1 cus goes to his/her cart place
         if(session != null){
             //2 cus goes to take his /her cart
            CartObject cart = (CartObject)session.getAttribute("CART");
            if(cart != null){
                //3 cus takes items from cart
                Map<String,Integer> items = cart.getItems();
                if(items != null){
                    //4 .show items
                      %>
                      <form action="DispatchController">
                      <table border="1">
                          <thead>
                              <tr>
                                  <th>No.</th>
                                  <th>Name</th>
                                  <th>Quantity</th>
                                  <th>Action</th>
                              </tr>
                          </thead>
                          <tbody>
                             <% 
                               int count = 0;
                               for(String key : items.keySet()){
                                   %>  
                                <tr>
                                  <td>
                                      <%=++count %>
                                  </td>
                                  <td>
                                      <%=key  %>
                                  </td>
                                  <td>
                                      <%= items.get(key) %>
                                  </td>
                                  <td>
                                      <input type="checkbox" name="chkItem" value= "<%= key %>" />
                                  </td>
                              </tr>
                              <%
                               }//end get each item from items
                             %> 
                             <tr>
                                 <td colspan="3">
                                     <a href="shopping.html">Add More Items</a>
                                 </td>
                                 <td>
                                     <input type="submit" value="Remove Selected Book" name="btAction" />
                                 </td> 
                             </tr>
                          </tbody>
                      </table>
                      </form>
        <%
                    
                    return;
                }// items has existed
            }// cart has existed
         }//session has existed
       %>
       <h2>
           No cart has existed
       </h2>
       <a href="shopping.html">Add More Items</a>
    </body>
</html>
