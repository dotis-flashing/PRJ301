<%-- 
    Document   : createUser
    Created on : Dec 6, 2022, 1:09:17 PM
    Author     : Admin
--%>

<%@page import="sample.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <title>Create User</title>
        <link rel="stylesheet" type="text/css" href="mystyles.css" media="screen" />
    </head>
    <body>
        <h1>Input your information: </h1>
        
        <form action="MainController" method="POST">
            User ID <input type="text" name="userID" required=""/>
            ${requestScope.Error_USER.userID}</br>
            Full Name <input type="text" name="fullName" required=""/>
            ${requestScope.Error_USER.fullName}</br>
            Role ID <input type="text" name="roleID" value="USER" readonly=""/></br>
            Password <input type="password" name="password" required=""/></br>
            Confirm <input type="password" name="confirm" required=""/>
            ${requestScope.Error_USER.confirm}</br>
            <div class="g-recaptcha" data-callback="recaptchaCallback" data-sitekey="6LcL6HYjAAAAAPNxnK2MgD4ozebfuVDg6DURntJR"></div>                            
            <input type="submit" name="action" value="Create"/>
            <input type="reset" value="Reset"/></br>
            <a href="login.jsp">Login</a>
            <p>${requestScope.MESSAGE}</p>
        </form>
    </body>
</html>
