package beans;
import interfaces.LibraryLocal;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import models.Book;
import models.Borrow;
import utils.Generator;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Stateless
@Remote(LibraryLocal.class)
public class Library implements LibraryLocal {

    @PersistenceContext(unitName = "ejb")
    private EntityManager entityManager;

    @Override
    public List<Book> getBooks() {
        return entityManager
                .createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    @Override
    public Optional<Book> getBookById(Integer id) {
        return entityManager
                .createQuery("select b from Book b where id=:id", Book.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<Borrow> getOpenBorrows() {
        return entityManager
                .createQuery("select b from Borrow b", Borrow.class)
                .getResultList();
    }

    @Override
    public Optional<Borrow> getBorrowById(Integer id) {
        return entityManager
                .createQuery("select b from Borrow b where id=:id", Borrow.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Override
    public void borrowBookById(Integer id, String borrowerName) throws RuntimeException {
        getBookById(id)
                .ifPresent(book -> {
                    if (book.getVolumes() <= 0) throw new RuntimeException("Cannot borrow book - not enough volumes!");
                    Borrow borrow = new Borrow();
                    borrow.setBorrowerName(borrowerName);
                    borrow.setBook(book);
                    borrow.setDate(new Date(System.currentTimeMillis()));;
                    entityManager.persist(borrow);
                    book.setVolumes(book.getVolumes() - 1);
                });
    }

    @Override
    public void returnBook(Borrow borrow) {
        entityManager.persist(borrow);
        borrow.setReturnDate(new Date(System.currentTimeMillis()));
        Book book = borrow.getBook();
        book.setVolumes(book.getVolumes() + 1);
        entityManager
                .createNativeQuery("delete from borrow where id= ?1")
                .setParameter(1, borrow.getId())
                .executeUpdate();
    }

    @Override
    public void returnBookById(Integer borrowId) {
        getBorrowById(borrowId)
                .ifPresent(this::returnBook);
    }

    @Override
    public void populateLibrary() {
        entityManager.createNativeQuery("delete from borrow").executeUpdate();
        entityManager.createNativeQuery("delete from book").executeUpdate();
        Generator.randomBooks().forEach(book -> entityManager.persist(book));
    }

    @Override
    public List<Book> search(String searchTerm) {
        if (Objects.equals(searchTerm, "")) return getBooks();
        return entityManager
                .createQuery("select b from Book b where title=:searchTerm or author=:searchTerm", Book.class)
                .setParameter("searchTerm", searchTerm)
                .getResultList();
    }

    @Override
    public void modifyBorrow(int id, String borrowerName) {
        entityManager
                .createNativeQuery("update borrow b set b.borrowerName=:borrowerName where b.id=:id", Borrow.class)
                .setParameter("borrowerName", borrowerName)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void modifyBook(int id, int volumes) {
        entityManager
                .createNativeQuery("update book b set b.volumes=:volumes where b.id=:id", Borrow.class)
                .setParameter("volumes", volumes)
                .setParameter("id", id)
                .executeUpdate();
    }
}