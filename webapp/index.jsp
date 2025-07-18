<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deadlock Stats</title>
</head>
<body>
    <h1>Deadlock Stats Application</h1>
    <p>JSP Server is running!</p>
    <p>Current Time: <%= new java.util.Date().toString() %></p>
    <p>Java Version: <%= System.getProperty("java.version") %></p>
</body>
</html>