<%--
  Created by IntelliJ IDEA.
  User: olik0
  Date: 26.03.2023
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <div class="form-group">
        <label for="userName">Nazwa</label>
        <input name="userName" type="text" class="form-control" id="userName" placeholder="Nazwa użytkownika">
    </div>
    <div class="form-group">
        <label for="userEmail">Email</label>
        <input name="userEmail" type="email" class="form-control" id="userEmail" placeholder="Email użytkownika">
    </div>
    <div class="form-group">
        <label for="userPassword">Hasło</label>
        <input name="userPassword" type="password" class="form-control" id="userPassword"
               placeholder="Hasło użytkownika">
    </div>

    <button type="submit" class="btn btn-primary">Zapisz</button>
</form>
</body>
</html>
