package interfaces;

import models.Book;
import models.Borrow;

import java.util.List;
import java.util.Optional;

public interface LibraryLocal {
    List<Book> getBooks();
    Optional<Book> getBookById(Integer id);
    List<Borrow> getOpenBorrows();
    Optional<Borrow> getBorrowById(Integer id);
    void borrowBookById(Integer id, String borrowerName) throws RuntimeException;
    void returnBook(Borrow borrow);
    void returnBookById(Integer borrowId);
    void populateLibrary();
    List<Book> search(String searchTerm);
    void modifyBorrow(int id, String borrowerName);
    void modifyBook(int id, int volumes);
}