package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");


        if ((action != null) && action.equals("add")) {
            resp.sendRedirect("add.jsp");
        }

        if ((action != null) && action.equals("addBook")) {
            BookController bookController = (BookController) req.getSession().getAttribute("bookController");
            bookController.add(req.getParameter("name"),
                    req.getParameter("genre"),
                    Double.parseDouble(req.getParameter("price")));
            resp.sendRedirect(req.getContextPath() + "");
        }

        if ((action != null) && action.equals("update")) {
            int index = Integer.parseInt(req.getParameter("index"));
            String name = req.getParameter("name");
            String genre = req.getParameter("genre");
            double price = Double.parseDouble(req.getParameter("price"));
            resp.sendRedirect(req.getContextPath() + "/update.jsp?index=" + index
                + "&name=" + name + "&genre=" + genre + "&price=" + price);
        }

        if ((action != null) && action.equals("delete")) {
            int index = Integer.parseInt(req.getParameter("index"));
            System.out.println(index);
            resp.sendRedirect(req.getContextPath() + "/delete.jsp?index=" + index);
        }

        if ((action != null) && action.equals("cancelDelete")) {
            resp.sendRedirect(req.getContextPath() + "");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ((action != null) && action.equals("deleteBook")) {
            int index = Integer.parseInt(req.getParameter("id"));
            BookController bookController = (BookController) req.getSession().getAttribute("bookController");
            bookController.delete(index);
            bookController.updateIds(index);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(req, resp);
        }

        if ((action != null) && action.equals("updateBook")) {
            int index = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String genre = req.getParameter("genre");
            double price = Double.parseDouble(req.getParameter("price"));
            BookController bookController = (BookController) req.getSession().getAttribute("bookController");
            bookController.update(index, name, genre, price);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(req, resp);
        }
    }
}
