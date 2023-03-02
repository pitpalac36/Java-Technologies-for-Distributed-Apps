<%@ page import="domain.Book" %>
<%@ page import="controller.BookController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BookController bookController = (BookController) session.getAttribute("bookController");
    int index = Integer.parseInt(request.getParameter("index")) - 1;
    Book book = bookController.getAll().get(index);
%>
<html>
<head>
    <title>Delete a Book</title>
</head>
<body>
<form method='POST' action='DispatcherController/'>
    <input type='hidden' name='action' value='deleteBook'>
    <input type='hidden' name='id' value='<%=book.getId()%>'>
    Are you sure you want to delete this entry?<br>
    <input type="submit" name="deleteBtn" value="Delete"><br>
</form>
<form method="GET" action="DispatcherController/">
    <input type='hidden' name='action' value='cancelDelete'>
    <input type='submit' value='Cancel'>
</form>
</body>
</html>
