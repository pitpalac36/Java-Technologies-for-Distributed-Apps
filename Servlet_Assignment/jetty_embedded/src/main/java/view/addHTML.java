package view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class addHTML extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form method='GET' action='/dispatcher'>");
        out.println("<input type='hidden' name='action' value='1'>");
        out.println("Title of the Book: <input type='text' name='name'>");
        out.println("Genre of the Book: <input type='text' name='genre'>");
        out.println("Price of the Book: <input type='text' name='price'>");
        out.println("<input type='submit' value='Add'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}