<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<style>
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
        font-size: 20px;
    }

    .link:hover {
        background-color: #7D8ABC;
        color: white;
    }
</style>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="ClassServlet" class="link">Show Classes</a>
<a href="StudentServlet" class="link">Show Student</a>

</body>
</html>