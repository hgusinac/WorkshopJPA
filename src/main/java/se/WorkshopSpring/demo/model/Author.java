package se.WorkshopSpring.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private int authorId;
    private String firstName;
    private String lastName;
    @ManyToMany(
            cascade = {CascadeType.DETACH,CascadeType.REFRESH}, fetch = FetchType.LAZY
    )

    private Set<Book> writtenBooks;

    public Author() {
    }

    public Author(int authorId, String firstName, String lastName, Set<Book> writtenBooks) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.writtenBooks = writtenBooks;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if ((firstName == null) || firstName.equals("")) throw new IllegalArgumentException("Firstname was null");

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if ((lastName == null) || lastName.equals("")) throw new IllegalArgumentException("LastName was null");

        this.lastName = lastName;
    }

    public Set<Book> getWrittenBooks() {
        return writtenBooks;
    }

    public void setWrittenBooks(Set<Book> writtenBooks) {
        if (writtenBooks == null) writtenBooks = new HashSet<>();

        if (writtenBooks.isEmpty()){
            if (this.writtenBooks !=null){
                this.writtenBooks.forEach(wrb -> wrb.getAuthors().remove(this));
            }
        }else {
            this.writtenBooks.forEach(wrb -> wrb.getAuthors().add(this));
        }

        this.writtenBooks = writtenBooks;
    }

    public void AddBook(Book book){
        if(book == null) throw new IllegalArgumentException("Book was null");

        writtenBooks.add(book);
        Set<Author> authors = book.getAuthors();
        authors.add(this);
        book.setAuthors(authors);

    }
    public void removeBook(Book book){
        if (book == null) throw new IllegalArgumentException("book was null");
        if (writtenBooks == null)writtenBooks = new HashSet<>();

        writtenBooks.remove(book);
        book.getAuthors().remove(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName) && Objects.equals(writtenBooks, author.writtenBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, firstName, lastName, writtenBooks);
    }

    public void addWrittenBook(Book book){
        if (writtenBooks == null) throw new IllegalArgumentException("Book was null");
        if (writtenBooks== null)  writtenBooks = new HashSet<>();

        writtenBooks.add(book);
        book.getAuthors().add(this);
    }

    public void removeWrittenBook(Book book){
        if (book == null) throw new IllegalArgumentException("book was null");
        if (!writtenBooks.contains(book)) throw new IllegalArgumentException("book is not found");

        writtenBooks.remove(book);
        book.getAuthors().remove(this);
    }
}
