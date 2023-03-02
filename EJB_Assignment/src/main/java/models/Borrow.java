package models;

import lombok.Builder;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;


@Entity
@Table(name="borrow")
public class Borrow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String borrowerName;
    private Date date;
    private Date returnDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="book_id")
    private Book book;

    public Borrow(String borrowerName, Date date, Date returnDate) {
        this.borrowerName = borrowerName;
        this.date = date;
        this.returnDate = returnDate;
    }

    public Borrow() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", borrowerName='" + borrowerName + '\'' +
                ", date=" + date +
                ", returnDate=" + returnDate +
                ", book=" + book +
                '}';
    }
}