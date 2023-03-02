package view;

import controller.BookController;
import repo.Repo;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class dispatcherHTML extends HttpServlet {
    private final BookController bookController = new BookController(new Repo());

    protected void procReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int action = 0;
        String actionView = "";
        int addFlag = 0;
        int updateFlag = 0;
        int deleteFlag = 0;
        String name = "";
        String genre = "";
        double price = 0.0;
        int index = 0;

        try {
            if (req.getParameter("deleteBtn").equals("Delete")) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/delete");
                rd.forward(req, resp);
                return;
            }
        }
        catch (Exception e) {
            System.out.println("Error when sending delete request!" + "\n" + e.getMessage());
            actionView = "";
        }

        try {
            if (req.getParameter("addBtn").equals("Add")) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/add");
                rd.forward(req, resp);
                return;
            }
        }
        catch (Exception e) {
            System.out.println("Error when sending add request!" + "\n" + e.getMessage());
            actionView = "";
        }

            try {

            if (req.getParameter("updateBtn").equals("Update")) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/update");
                rd.forward(req, resp);
                return;
            }
        } catch (Exception e) {
            System.out.println("Error when sending update request!" + "\n" + e.getMessage());
            actionView = "";
        }

        try {
            action = Integer.parseInt(req.getParameter("action"));
        }
        catch (Exception e) {
            System.out.println("Error when receiving response for dispatcher!\n" + e.getMessage());
        }
        switch (action) {
            case 1:
                addFlag = 1;
                break;
            case 2:
                updateFlag = 1;
                break;
            case 3:
                deleteFlag = 1;
                break;
            default:
                break;
        }
        if (addFlag == 1) {
            try {
                name = req.getParameter("name");
                genre = req.getParameter("genre");
                price = Double.parseDouble(req.getParameter("price"));
                bookController.add(name, genre, price);
            }
            catch (Exception e) {
                System.out.println("Error when adding!\n" + e.getMessage());
            }
        }

        if (updateFlag == 1) {
            try {
                name = req.getParameter("name");
                genre = req.getParameter("genre");
                price = Double.parseDouble(req.getParameter("price"));
                index = Integer.parseInt(req.getParameter("id"));
                bookController.update(index, name, genre, price);
            }
            catch (Exception e) {
                System.out.println("Error when updating!\n" + e.getMessage());
            }
        }

        if (deleteFlag == 1) {
            try {
                index = Integer.parseInt(req.getParameter("id"));
                bookController.delete(index);
                bookController.updateIds(index);
            }
            catch (Exception e) {
                System.out.println("Error when deleting!\n" + e.getMessage());
            }
        }

        req.setAttribute("controller", bookController);

        System.out.println("actionView: " + actionView);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procReq(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procReq(req, resp);
    }
}
