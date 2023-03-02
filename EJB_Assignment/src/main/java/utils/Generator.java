package utils;

import models.Book;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    private Generator() {}

    public static Book randomBook() {
        com.github.javafaker.Book fakeBook = faker.book();
        return new Book(fakeBook.title(), fakeBook.author(), random.nextInt(5) + 1);
    }

    public static List<Book> randomBooks() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i <= random.nextInt(10) + 10; i++) {
            books.add(randomBook());
        }
        return books;
    }
}