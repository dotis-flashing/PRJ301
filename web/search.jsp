<%-- 
    Document   : search
    Created on : Dec 1, 2022, 12:36:23 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="sample.food.FoodDTO"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'ADMIN'}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>
        <p>Welcome:${sessionScope.LOGIN_USER.fullName}</p>
        <form action="MainController">
            <input type="submit" name="action" value="Logout"/>
        </form>
        <!--logout jstl-->
        <c:url var="logoutLink" value="MainController">
            <c:param name="action" value="Logout"></c:param>
        </c:url>
        <form action="MainController">
            Search <input type="text" name="search" value="${param.search}"/>
            <input type="submit" name="action" value="Search"/>
        </form>
        <c:if test="${requestScope.LIST_FOOD != null}">
            <c:if test="${not empty requestScope.LIST_FOOD}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Cooking Time</th>
                            <th>Status</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="food" varStatus="counter" items="${requestScope.LIST_FOOD}">
                        <form action="MainController" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    <input type="text" name="id" value="${food.id}" readonly=""/>
                                </td>
                                <td>
                                    <input type="text" name="name" value="${food.name}" required=""/>

                                </td>
                                <td>
                                    <input type="text" name="description" value="${food.description}" required=""/>

                                </td>
                                <td>
                                    <input type="number" name="price" value="${food.price}" required="" min="1"/>
                                </td>
                                <td>
                                    <input type="number" name="cookingTime" value="${food.cookingTime}" required=""/>
                                </td>
                                <td>${food.status}</td>
                                
                                <td>
                                    <input type="submit" name="action" value="Delete"/>
                                    <input type="hidden" name="search" value="${param.search}"/>
                                </td>
                                <td>
                                    <input type="submit" name="action" value="Update"/>
                                    <input type="hidden" name="search" value="${param.search}"/>
                                </td>
                            </tr>
                        </form>

                    </c:forEach>
                </tbody>
            </table>

        </c:if>
    </c:if>
            <a href="insertFood.jsp">Insert food</a>
</body>
</html>
