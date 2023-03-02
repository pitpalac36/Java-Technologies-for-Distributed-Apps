<%@ page import="domain.Book" %>
<%@ page import="controller.BookController" %>
<%@ page import="domain.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! BookController bookController;%>
<%
    bookController = (BookController) session.getAttribute("bookController");
    int index = Integer.parseInt(request.getParameter("index")) - 1;
    Book book = bookController.getAll().get(index);
%>
<html>
<head>
    <title>Update a Book</title>
</head>
<body>
<form method="POST" action="DispatcherController/">
    <input type="hidden" name="action" value="updateBook">
    <input type="hidden" name="id" value="<%=book.getId()%>">
    New title: <input type="text" name="name" value="<%=book.getName()%>"><br>
    New genre: <input type="text" name="genre" value="<%=book.getGenre()%>"><br>
    New price: <input type="text" name="price" value="<%=book.getPrice()%>"><br>
    <input type="submit" value="Update">
</form>
</body>
</html>
