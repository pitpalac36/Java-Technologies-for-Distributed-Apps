package view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class updateHTML extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = "";
        String genre = "";
        double price = 0.0;
        int index = 0;

        try {
            name = req.getParameter("name");
            genre = req.getParameter("genre");
            price = Double.parseDouble(req.getParameter("price"));
            index = Integer.parseInt(req.getParameter("index"));
        }
        catch (Exception e) {
            System.out.println("Error when receiving data to update!");
        }

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form method='GET' action='/dispatcher'>");
        out.println("<input type='hidden' name='action' value='2'>");
        out.println("<input type='hidden' name='id' value='" + index + "'>");
        out.println("Title of the Book: <input type='text' name='name' value='" + name + "'>");
        out.println("Genre of the Book: <input type='text' name='genre' value='" + genre + "'>");
        out.println("Price of the Book: <input type='text' name='price' value='" + price + "'>");
        out.println("<input type='submit' value='Update'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}