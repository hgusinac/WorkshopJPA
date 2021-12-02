package se.WorkshopSpring.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",updatable = false)
    public int bookId;
    private String isbn;
    @Column(length = 40)
    private String title;
    private int maxLoanDays;
    private boolean available=true;
    @ManyToMany(
            cascade = {CascadeType.DETACH,CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private Set<Author> authors = new HashSet<>();

    public Book(int bookId, String isbn, String title, int maxLoanDays) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        if (authors == null) authors = new HashSet<>();
        if (authors.isEmpty()){
            if (this.authors!=null){
                this.authors.forEach(author -> author.getWrittenBooks().remove(this));
            }
        }else{
            authors.forEach(author -> author.getWrittenBooks().add(this));
        }
        this.authors = authors;
    }

    public void addAuthor(Author author){
        if (author == null) throw  new IllegalArgumentException("Authors was null");
        authors.add(author);
        Set<Book> books = author.getWrittenBooks();
        books.add(this);
        author.setWrittenBooks(books);
    }
}
