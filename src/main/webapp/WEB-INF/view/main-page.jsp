

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
                <style>
                 table {
                   font-family: arial, sans-serif;
                   border-collapse: collapse;
                   width: 100%;
                 }

                 td, th {
                   border: 1px solid #dddddd;
                   text-align: left;
                   padding: 8px;
                 }

                 tr:nth-child(even) {
                   background-color: #dddddd;
                 }
                </style>    
    </head>
    <body>
        <h1>You're on the main page!</h1>
        <p>${welcomeMessage}</p>
        
        
            
                    <table>
          <tr>
            <th>Name</th>
            <th>Age</th>
            <th>Email</th>
          </tr>
          <c:forEach items="${users}" var="user">
          <tr>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
          </tr>
        </c:forEach>
        </table>
    </body>
</html>
