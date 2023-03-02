package servlets;
import interfaces.LibraryLocal;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Book;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = { "/populate", "/dispatcher" })
public class BookServlet extends HttpServlet {
    @EJB
    private LibraryLocal library;

    public BookServlet(){}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("modifyBook") != null) {
            library.modifyBook(
                    Integer.parseInt(req.getParameter("id")),
                    Integer.parseInt(req.getParameter("volumes")));
        } else if (req.getParameter("modifyBorrow") != null) {
                library.modifyBorrow(
                        Integer.parseInt(req.getParameter("id")),
                        req.getParameter("borrowerName"));

        } else {
            String action = req.getParameter("action");
            switch (action) {
                case "populate":
                    library.populateLibrary();
                    break;
                case "borrow":
                    try {
                        if (req.getParameter("borrowerName").length() == 0)
                            throw new RuntimeException("Borrower name must be filled!");
                        library.borrowBookById(
                                Integer.parseInt(req.getParameter("id")),
                                req.getParameter("borrowerName")
                        );
                    } catch (RuntimeException e) {
                        if (e.getCause() == null) req.getSession().setAttribute("error", e.getMessage());
                        else req.getSession().setAttribute("error", e.getCause().getMessage());
                        throw new RuntimeException(e.getMessage());
                    }
                    break;
                case "return":
                    library.returnBookById(Integer.parseInt(req.getParameter("id")));
                    break;
            }
        }
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        session.setAttribute("borrows", library.getOpenBorrows());
        String action = req.getParameter("action");
        if (action.equals("search")) {
            session.setAttribute("books", library.search(req.getParameter("search")));
        } else {
            session.setAttribute("books", library.getBooks());
        }
        req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}