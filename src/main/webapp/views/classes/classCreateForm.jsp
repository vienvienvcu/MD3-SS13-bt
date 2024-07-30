<%--
  Created by IntelliJ IDEA.
  User: viennguyenthi
  Date: 2024/07/29
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Class</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #2F3645;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 10px;
            color: #2F3645;
            font-size: 18px;
            font-weight: 700;
        }
        p{
            margin-bottom: 10px;
            color: #2F3645;
            font-size: 18px;
            font-weight: 700;
        }
        input[type="text"] {
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }
        select {
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }
        input[type="submit"] {
            background-color: #304463;
            color: #FFC7ED;
            border: 1px solid #2F3645;
            padding: 10px;
            text-align: center;
            border-radius: 10px;
            font-weight: 800;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #7D8ABC;
            color: white;
        }
        .link {
            text-decoration: none;
            background-color: #304463;
            color: #FFC7ED;
            border: 1px solid #2F3645;
            padding: 10px 12px;
            text-align: center;
            display: inline-block;
            border-radius: 3px;
            font-weight: 800;
            font-size: 16px;
            margin-top: 20px;
        }
        .link:hover {
            background-color: #7D8ABC;
            color: white;
        }
        .btn-group label {
            display: inline-block;
            margin-right: 10px;
            background-color: #304463;
            color: #FFC7ED;
            border: 1px solid #2F3645;
            padding: 6px 12px;
            border-radius: 10px;
            font-weight: 800;
            font-size: 16px;
            cursor: pointer;
        }
        .btn-group input[type="radio"] {
            display: none;
        }
        .btn-group label:hover {
            background-color: #7D8ABC;
            color: white;
        }

    </style>
</head>
<body>
<div class="container">
    <h2>Create New Class</h2>
    <form action="/ClassServlet" method="post">
        <input type="hidden" name="action" value="add"/>

        <label for="classId">Class ID:</label>
        <input type="text" id="classId" name="classId" required>

        <label for="className">Class Name:</label>
        <input type="text" id="className" name="className" required>

        <p>Status:</p>
        <div class="btn-group">
            <label>
                <input type="radio" name="status" value="true" required> Active
            </label>
            <label>
                <input type="radio" name="status" value="false" required> Inactive
            </label>
        </div>

        <input type="submit" value="Add Class">
    </form>
    <a href="/classes" class="link"><< Back</a>
</div>
</body>
</html>

