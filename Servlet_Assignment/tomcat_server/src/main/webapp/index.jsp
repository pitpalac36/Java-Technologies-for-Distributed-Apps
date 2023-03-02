<%@ page import="controller.BookController" %>
<%@ page import="repo.Repo" %>
<%@ page import="domain.Book" %>
<%@ page import="domain.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! final BookController bookController = new BookController(new Repo());%>
<%
  if (session.getAttribute("bookController") == null) {
    session.setAttribute("bookController", bookController);
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>View Page</title>
  <style>
    table, th, td {
      border: 1px solid black;
    }
    table {
      width: 10%;
      margin-left: 100px;
      margin-bottom: 15px;
    }
    #add {
      margin-left: 100px;
    }
  </style>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="js/utils.js"></script>
</head>
<body>
<table>
  <tr>
    <th>Name</th>
    <th>Genre</th>
    <th>Price</th>
  </tr>
  <%
    for (Book book : bookController.getAll()) {
  %>
  <tr>
    <form action="DispatcherController/" method="GET">
      <input type="hidden" name="action" value="update">
      <input type="hidden" name="index" value="<%=book.getId()%>">
      <td><input type="text" name="name" value="<%=book.getName()%>" readonly></td>
      <td><input type="text" name="genre" value="<%=book.getGenre()%>" readonly></td>
      <td><input type="text" name="price" value="<%=book.getPrice()%>" readonly></td>
      <td><input type="submit" name="updateBtn" value="Update"></td>
    </form>
    <form action="DispatcherController/" method="GET">
      <input type="hidden" name="action" value="delete">
      <input type="hidden" name="index" value="<%=book.getId()%>">
      <td><input type="submit" name="deleteBtn" value="Delete"></td>
    </form>
  </tr>
  <%
    }
  %>
</table>
<button id="add">Add a Book</button>
<script>
  $("#add").on("click", goAdd);
</script>
</body>
</html>