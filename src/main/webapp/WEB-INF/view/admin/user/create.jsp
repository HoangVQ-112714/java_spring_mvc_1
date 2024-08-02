<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Create User</title>

    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <link rel="stylesheet" href="/css/demo.css">
</head>
<body>

<form:form method="post" action="/admin/user/create" modelAttribute="newUser">
    <div class="mb-3">
        <label for="userMail" class="form-label">Mail</label>
        <form:input type="email" class="form-control" id="userMail" path="email"/>
    </div>
    <div class="mb-3">
        <label for="userPass" class="form-label">Password</label>
        <form:input type="password" class="form-control" id="userPass" path="password"/>
    </div>
    <div class="mb-3">
        <label for="userName" class="form-label">Name</label>
        <form:input type="text" class="form-control" id="userName" path="name"/>
    </div>
    <div class="mb-3">
        <label for="userAddress" class="form-label">Address</label>
        <form:input type="text" class="form-control" id="userAddress" path="address"/>
    </div>
    <div class="mb-3">
        <label for="userPhone" class="form-label">Phone</label>
        <form:input type="tel" class="form-control" id="userPhone" path="phone"/>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form:form>

</body>
</html>