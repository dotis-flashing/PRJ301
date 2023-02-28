<%-- 
    Document   : user
    Created on : Dec 1, 2022, 12:40:28 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'ADMIN'}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>
        
        User ID: ${sessionScope.LOGIN_USER.userID}</br>
        Full Name: ${sessionScope.LOGIN_USER.fullName}</br>
        Role ID: ${sessionScope.LOGIN_USER.roleID}</br>
        Password: ${sessionScope.LOGIN_USER.password}</br>
    </body>
</html>
