<%-- 
    Document   : createAccount
    Created on : Jun 22, 2022, 8:00:27 AM
    Author     : Admin
--%>

<%--<%@page import="thuongng.registration.RegistrationCreateError"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>Create new Account</h1>
        <form action="createAccount" method="POST"> 
            <c:set var="errors" value="${requestScope.CREATE_ERROR}" /> 
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />e.g 6-20 chars <br/> 
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color = "red">
                ${errors.usernameLengthErr}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.userIsExisted}">
                <font color = "red">
                ${errors.userIsExisted}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" />e.g 6- 30 chars <br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color = "red">
                ${errors.passwordLengthErr}
                </font><br/>
            </c:if>
            Confirm*  <input type="password" name="txtConfirm" value="" /> <br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color = "red">
                  ${errors.confirmNotMatched}
                </font><br/>
            </c:if>
            Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" />e.g 2-50 chars <br/>
            <c:if test="${not empty errors.fullnameLengthErr}">
                <font color = "red">
                ${errors.fullnameLengthErr}
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
            <form action="loginPage">
                <input type="submit" value="Back To Login Page" />
            </form>
        <%--   <%
               RegistrationCreateError errors = (RegistrationCreateError) request.getAttribute("CREATE_ERROR");
           %>
           Username* <input type="text" name="txtUsername" value="<%= request.getParameter("txtUsername")%>" />e.g 6-20 chars <br/>
           <%
               if (errors.getUsernameLengthErr() != null) {
           %>  
           <font color="red">
           <%= errors.getUsernameLengthErr()%>
           </font></br>
           <%
               }
           %>
           <%
               if (errors.getUserIsExisted() != null) {
           %>  
           <font color="red">
           <%= errors.getUserIsExisted()%>
           </font></br>
           <%
               }
           %>
           Password* <input type="password" name="txtPassword" value="<%=request.getParameter("txtPassword")%>" />e.g 6- 30 chars <br/><br/>
           <%
               if (errors.getPasswordLengthErr() != null) {
           %>  
           <font color="red">
           <%= errors.getPasswordLengthErr()%>
           </font></br>
           <%
               }
           %>
           Confirm*  <input type="password" name="txtConfirm" value="<%= request.getParameter("txtConfirm")%>" /> <br/>
           <%
               if (errors.getConfirmNotMatched() != null) {
           %>  
           <font color="red">
           <%= errors.getConfirmNotMatched()%>
           </font></br>
           <%
               }
           %>
           Full name* <input type="text" name="txtFullname" value="<%= request.getParameter("txtFullname")%>" />e.g 2-50 chars <br/>
           <%
               if (errors.getFullnameLengthErr() != null) {
           %>  
           <font color="red">
           <%= errors.getFullnameLengthErr()%>
           </font></br>
           <%
              }
           %>
           <input type="submit" value="Create New Account" name="btAction" />
           <input type="reset" value="Reset" />
           <%
           %> --%>
    </body>
</html>
