<%-- 
    Document   : JSP
    Created on : Jun 8, 2022, 7:45:17 AM
    Author     : Admin
--%>


<%--%<%@page import="java.util.List"%>
<%@page import="java.lang.String"%>
<%@page import="thuongng.registration.RegistrationDTO"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.FULLNAME} 
        </font>
        <form action = "logout">
            <input type="submit" value="Logout" name="btAction" />
        </form>
        <form action="searchLastname">
            Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /></br>
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        <c:set var ="searchValue" value = "${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}"  varStatus="counter">
                        <form action="updateAccount">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="password" name="txtPassword" value="${dto.password}" /><br/>
                                    <c:if test="${not empty requestScope.CREATE_ERROR_UPDATE.passwordIsEmptyError and requestScope.ERROR_PK== dto.username}">
                                        <font color = "red">
                                        ${requestScope.CREATE_ERROR_UPDATE.passwordIsEmptyError}
                                        </font><br/>
                                    </c:if>
                                </td>
                                <td>
                                    <input type="text" name="txtLastName" value="${dto.lastName}"  /><br/>
                                    <c:if test="${not empty requestScope.CREATE_ERROR_UPDATE.fullnameIsEmptyError and requestScope.ERROR_PK == dto.username}">
                                        <font color = "red">
                                        ${requestScope.CREATE_ERROR_UPDATE.fullnameIsEmptyError}
                                        </font><br/>
                                    </c:if>
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}" >
                                               checked ="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="deleteAccount">
                                        <c:param name="btAction" value="Delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="role" value="${dto.role}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>No record is matched</h2>
        </c:if>
    </c:if>
            <c:if test="${sessionScope.deleteError ne null }">
                <font color ="red"> ${sessionScope.deleteError} </font>
            </c:if>

    <%--   <% 
         String fullname = (String)session.getAttribute("FULLNAME");
         %>
         <font color="red">
            Welcome, ${sessionScope.FULLNAME} 
        </font>
         <%
        %>
        <a href="DispatchController?btAction=Logout">Logout</a>
        <%
        %>

        <h1>Search Page</h1>
        <form action="DispatchController">
            Search Value <input type="text" name="txtSearchValue" value=" <%= request.getParameter("txtSearchValue")%>" /></br>
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchController"
                                + "?btAction=Delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;
                %>
            <form action="DispatchController">
                <tr>
                    <td>
                        <%= ++count%>
                        .</td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getlastName()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ON"
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   }//end admin role is true
%>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction" />
                        <input type="hidden" name="lastSearchValue" value="<%= searchValue%>" />
                    </td>
                </tr>
            </form>
            <%
                }//end for dto in result
            %>
        </tbody>
    </table>

    <%
    } else {
    %>
    <h2>
        No record is matched!!
    </h2>
    <%
            }
        }//end if search Value has valid
%>  --%>

</body>
</html>