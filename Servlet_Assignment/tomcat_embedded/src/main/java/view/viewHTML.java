package view;

import controller.BookController;
import domain.Book;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class viewHTML extends HttpServlet {
    private BookController bookController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try {
            bookController = (BookController) req.getAttribute("controller");
        }
        catch (Exception e) {
            System.out.println("Error when receiving controller");
        }
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<style = 'text/css'>" +
                "table, th, td {" +
                "   border: 1px solid black;" +
                "}" +
                "table {" +
                "   width: 10%;" +
                "   margin-left: 100px;" +
                "   margin-bottom: 15px;" +
                "}" +
                "#add {" +
                "   margin-left: 100px;" +
                "}" +
                "</style>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>" + "Name" + "</th>");
        out.println("<th>" + "Genre" + "</th>");
        out.println("<th>" + "Price" + "</th>");
        out.println("</tr>");
        for (Book book: bookController.getAll()) {
            out.println("<form method='GET' action='/dispatcher'>");
            out.println("<tr>");
            out.println("<input type='hidden' name='index' value='" + book.getId() + "'>");
            out.println("<td>" + "<input type='text' name='name' value='"+book.getName() +"'>" + "</td>");
            out.println("<td>" + "<input type='text' name='genre' value='"+book.getGenre() +"'>" + "</td>");
            out.println("<td>" + "<input type='text' name='price' value='"+book.getPrice() +"'>" + "</td>");
            out.println("<td>" + "<input type='submit' name='updateBtn' value='Update'>" + "</td>");
            out.println("<td>" + "<input type='submit' name='deleteBtn' value='Delete'>" + "</td>");
            out.println("</tr>");
            out.println("</form>");
        }
        out.println("</table>");
        out.println("<form method='GET' action='/add'>");
        out.println("<input id='add' type='submit' value='Add a Book'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}