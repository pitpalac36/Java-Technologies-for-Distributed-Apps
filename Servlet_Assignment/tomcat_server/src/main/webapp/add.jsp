<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a Book</title>
</head>
<body>
<form method="GET" action="DispatcherController/">
    <input type="hidden" name="action" value="addBook">
    Title of the Book: <input id="addName" type='text' name='name'><br>
    Genre of the Book: <input id="addGenre" type='text' name='genre'><br>
    Price of the Book: <input id="addPrice" type='text' name='price'><br>
    <input type="submit" name="addBtn" value="Add">
</form>
</body>
</html>
