<%@ page import="com.example.model.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Usuarios</title>
    <title>Usuarios</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h1, h2 {
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        button, input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            margin: 10px 0;
            border: none;
            cursor: pointer;
        }

        button:hover, input[type="submit"]:hover {
            background-color: #45a049;
        }

        form {
            margin-bottom: 20px;
        }

        input[type="text"] {
            padding: 5px;
            margin: 5px 0;
            box-sizing: border-box;
            width: 100%;
            max-width: 300px;
        }

        a {
            color: #4CAF50;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>Gestión de Usuarios</h1>
<form action="UserServlet" method="get">
    <input type="hidden" name="action" value="list">
    <button type="submit">Consultar Usuarios</button>
</form>


<!-- Verificar si hay usuarios -->
<h2>Lista de Usuarios</h2>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Email</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<User> userList = (List<User>) request.getAttribute("users");
        if (userList != null) {
            for (User user : userList) {
    %>
    <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getName() %></td>
        <td><%= user.getEmail() %></td>
        <td><a href="UserServlet?action=delete&userId=<%= user.getId() %>">Eliminar</a></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>


<h2>Agregar Nuevo Usuario</h2>
<form action="UserServlet" method="post">
    Nombre: <input type="text" name="name" /> <br />
    Email: <input type="text" name="email" /> <br />
    <input type="submit" value="Guardar" />
</form>
</body>
</html>
