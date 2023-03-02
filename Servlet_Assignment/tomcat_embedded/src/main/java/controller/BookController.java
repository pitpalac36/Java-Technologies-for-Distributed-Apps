package controller;

import domain.Book;
import repo.Repo;
import java.util.List;

public class BookController {
    private Repo repo;

    public BookController(Repo repo) {
        this.repo = repo;
        init();
    }

    private void init() {
        Book book1 = new Book("Gone with the Wind", "history & drama", 30.5);
        Book book2 = new Book("Three men in a boat", "comedy", 13.5);
        Book book3 = new Book("Robinson Crusoe", "adventure", 21.9);

        repo.add(book1);
        repo.add(book2);
        repo.add(book3);
    }

    public void add(String name, String genre, double price) {
        this.repo.add(new Book(name, genre, price));
    }

    public void delete(int index) {
        this.repo.removeBookAt(index - 1);
        Book.count.decrementAndGet();
    }

    public void update(int index, String name, String genre, double price) {
        Book book = this.repo.getBookAt(index - 1);
        book.setName(name);
        book.setGenre(genre);
        book.setPrice(price);
    }

    public List<Book> getAll() {
        return this.repo.getBookList();
    }

    @Override
    public String toString() {
        return "controller.BookController{" +
                "repo=" + repo +
                '}';
    }

    public void updateIds(int index) {
        int size = repo.getBookList().size();
        for (int i = index; i <= size; i++) {
            repo.getBookAt(i - 1).setId(i);
        }
    }
}