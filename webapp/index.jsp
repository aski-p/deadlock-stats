<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deadlock Stats</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        .status {
            color: #28a745;
            font-weight: bold;
        }
        .info {
            background-color: #e7f3ff;
            padding: 15px;
            border-radius: 5px;
            margin: 10px 0;
        }
        .links {
            margin-top: 20px;
        }
        .links a {
            display: inline-block;
            padding: 10px 20px;
            margin: 5px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .links a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🎮 Deadlock Stats Application</h1>
        <p class="status">✅ JSP is working!</p>
        
        <div class="info">
            <strong>Server Information:</strong><br>
            📅 Server Time: <%= new java.util.Date() %><br>
            🆔 Session ID: <%= session.getId() %><br>
            🌐 Server Info: <%= application.getServerInfo() %><br>
            ☕ Java Version: <%= System.getProperty("java.version") %>
        </div>
        
        <div class="links">
            <a href="deadlock-stats.jsp">View Deadlock Stats</a>
            <a href="health.jsp">Health Check</a>
        </div>
        
        <hr>
        <p><small>🚀 Deployed on Railway • Java/Tomcat JSP Application</small></p>
    </div>
</body>
</html>