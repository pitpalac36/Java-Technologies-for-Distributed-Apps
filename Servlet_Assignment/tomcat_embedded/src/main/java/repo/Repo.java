package repo;
import domain.Book;
import java.util.ArrayList;
import java.util.List;

public class Repo {
    private List<Book> bookList;

    public Repo() {
        this.bookList = new ArrayList<>();
    }

    public void add(Book book) { this.bookList.add(book); }

    public void removeBookAt(int index) { this.bookList.remove(index); }

    public Book getBookAt(int index) {return this.bookList.get(index); }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "repo.Repo{" +
                "bookList=" + bookList +
                '}';
    }
}