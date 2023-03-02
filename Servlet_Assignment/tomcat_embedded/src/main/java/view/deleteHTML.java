package view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class deleteHTML extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        int index = 0;

        try {
            index = Integer.parseInt(req.getParameter("index"));
        }
        catch (Exception e) {
            System.out.println("Error when fetching delete data");
        }

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form method='GET' action='/dispatcher'>");
        out.println("<input type='hidden' name='action' value='3'>");
        out.println("<input type='hidden' name='id' value='" + index + "'>");
        out.println("Are you sure you want to delete this entry?");
        out.println("<input type='submit' value='Delete'>");
        out.println("</form>");
        out.println("<form method='GET' action='/dispatcher'>");
        out.println("<input type='hidden' name='action' value='0'>");
        out.println("<input type='submit' value='Cancel'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}