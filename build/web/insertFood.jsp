<%-- 
    Document   : insertFood
    Created on : Dec 13, 2022, 3:25:40 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            Food ID <input type="text" name="ID" required=""/></br>
            Name <input type="text" name="name" required=""/></br>          
            Description <input type="text" name="description" required=""/></br>  
            Price <input type="text" name="price" required=""/></br>  
            Cooking time <input type="text" name="cookingTime" required=""/></br>  
            <input type="submit" name="action" value="Insert"/></br>
        </form>
    </body>
</html>
