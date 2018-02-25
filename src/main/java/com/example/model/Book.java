package com.example.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.net.URL;
import java.sql.Timestamp;

@XmlRootElement
@Entity
@NamedQuery(name = "Book.tableSize", query = "SELECT count(b) FROM Book b")
public class Book implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @NotEmpty(message = "{com.example.model.Book.title.NotEmpty}")
    private String title;
    @NotEmpty(message = "{com.example.model.Book.author.NotEmpty}")
    private String author;
    @NotEmpty(message = "{com.example.model.Book.isbn.NotEmpty}")
    @Size(min = 13, max=13, message ="{com.example.model.Book.isbn.Size}" )
    private String isbn;
    private Timestamp timestamp;
    private URL imageUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser", referencedColumnName = "userId")
    private User user;

    public Book() {
    }

    public Book(String title, String author, String isbn, Timestamp date, URL imageUrl) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.timestamp = date;
        this.imageUrl = imageUrl;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp date) {
        this.timestamp = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", date=" + timestamp +
                ", imageUrl=" + imageUrl +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookId != null ? !bookId.equals(book.bookId) : book.bookId != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (imageUrl != null ? !imageUrl.equals(book.imageUrl) : book.imageUrl != null) return false;
        return user != null ? user.equals(book.user) : book.user == null;
    }

    @Override
    public int hashCode() {
        int result = bookId != null ? bookId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
