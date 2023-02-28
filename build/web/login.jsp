<%-- 
    Document   : login
    Created on : Dec 1, 2022, 11:48:08 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="mystyles.css" media="screen" />
        <script src='https://www.google.com/recaptcha/api.js'></script>
    </head>
    <body>
        <form action="MainController" method="POST">
            <p>LOGIN</p>
            UserID <input type="text" name="userID" required=""/></br>
            Password <input type="password" name="password" required=""/></br>
            <div class="g-recaptcha" data-callback="recaptchaCallback" data-sitekey="6LcL6HYjAAAAAPNxnK2MgD4ozebfuVDg6DURntJR"></div>                            
            <input type="submit" name="action" value="Login"/></br>
            <input type="reset" value="Reset"/></br>
            <a href="createUser.jsp">Create User</a>
            <p>${requestScope.ERROR}</p>   
        </form>
    </body>
</html>
