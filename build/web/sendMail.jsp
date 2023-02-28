<%@page language="java" contentType="text/html ; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <title>Send Information Of Order</title>

    </head>
    <body>
        <div class="container" style="margin-top: 10px;">
            <div class="row"
                 style="border: 1px darkgray solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
                <div class="col-sm-12">
                    <h2 class="myclass">Input your Email to send</h2>
                    <form action="MainController" method="POST">
                        <div class="form-group">
                            <label>Your email: </label>
                            <input type="text" class="form-control" name="email" placeholder="Your email" required autofocus>
                        </div>
                        <button type="submit" class="btn btn-primary" name="action" value="SendMail"
                                onclick="return confirm('Send to this email?')">Send</button>
                        <a href="MainController?action=View" class="btn btn-primary"><span>Back</span></a>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>