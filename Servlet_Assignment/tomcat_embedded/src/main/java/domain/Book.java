package domain;

import java.util.concurrent.atomic.AtomicInteger;

public class Book {
    private int id = 0;
    public static final AtomicInteger count = new AtomicInteger(0);
    private String name;
    private String genre;
    private double price;


    public Book(String name, String genre, double price) {
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.id = count.incrementAndGet();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                '}';
    }
}
