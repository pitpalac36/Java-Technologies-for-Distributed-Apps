<%@ page import="models.Book" %>
<%@ page import="models.Borrow" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Library</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<form method="POST" action="${pageContext.request.contextPath}/populate" class="col-md-4">
    <input type="hidden" name="action" value="populate" />
    <button type="submit" class="btn btn-primary">ADD BOOKS TO SHELVES</button>
</form>
<hr />

<form method="POST" action="${pageContext.request.contextPath}/dispatcher" class="col-md-4">
    <h1>Available Books</h1>
    <input type="hidden" name="action" value="search">
    <input id="search" type='text' name='search'>
    <input type="submit" name="searchBtn" value="Search">
</form>

<table>
    <% List<Book> books = (List<Book>) session.getAttribute("books");
    List<Book> searched = (List<Book>) session.getAttribute("searched");
    if (books == null) {
        out.println("There are currently no books");
    } else {
        for (Book b : books) {
    %>
    <tr>
        <form method="POST" action="${pageContext.request.contextPath}/dispatcher">
            <input type="hidden" name="action" value="borrow">
            <input type="hidden" name="id" value="<%=b.getId()%>" readonly>

            <td><input name="title" value="<%=b.getTitle()%>" readonly></td>
            <td><input name="author" value="<%=b.getAuthor()%>" readonly></td>
            <td><input name="volumes" value="<%=b.getVolumes()%>"></td>

            <td><input type="text" name="borrowerName" /></td>
            <td><input type="submit" name="rentBtn" value="Borrow"></td>

            <td><input type="submit" name="modifyBook" value="Modify"></td>
        </form>
    </tr>
    <%
        } }
    %>
</table>

<div class="col-md-4">
    <h1>Current Borrows</h1>
</div>

<table>
    <% List<Borrow> borrows = (List<Borrow>)session.getAttribute("borrows");
       if (borrows == null) {
           out.println("There are currently no borrows");
       } else
        for (Borrow b : borrows) {
    %>
    <tr>
        <form method="POST" action="${pageContext.request.contextPath}/dispatcher">
            <input type="hidden" name="action" value="return">
            <input type="hidden" name="id" value="<%=b.getId()%>" readonly>

            <td><input name="title" value="<%=b.getBook().getTitle()%>" readonly></td>
            <td><input name="borrowerName" value="<%=b.getBorrowerName()%>"></td>
            <td><input name="date" value="<%=b.getDate()%>" readonly></td>
            <td><input type="submit" name="rent" value="Return"></td>

            <td><input type="submit" name="modifyBorrow" value="Modify"></td>
        </form>
    </tr>
    <%
        }
    %>
</table>

</body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</html>
