package se.WorkshopSpring.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private int appUserId;
    @Column(unique = true)
    private String username;
    private String password;
    private LocalDate regDate;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "fk_details_id",table = "app_user")
    private Details userDetails;

    @OneToMany(
            cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE},
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "borrower"
    )
    private List<BookLoan> bookLoans = new ArrayList<>();


    public AppUser(int appUserId, String username, String password, LocalDate regDate, Details userDetails) {
        this.appUserId = appUserId;
        this.username = username;
        this.password = password;
        this.regDate = regDate;
        this.userDetails = userDetails;
    }

    public AppUser() {
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Details getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Details userDetails) {
        this.userDetails = userDetails;
    }

    public void addBookLoan(BookLoan bookLoan){
        if (bookLoan==null) throw new IllegalArgumentException("BookLoan was null");
        if (bookLoan.getBook()==null) throw new IllegalArgumentException("no books to loan");

        bookLoan.getBook().setAvailable(false);
        bookLoans.add(bookLoan);
        bookLoan.setBorrower(this);

    }
    public void removeBookLoan(BookLoan bookLoan){
        if (bookLoan==null) throw new IllegalArgumentException("BookLoan was null");
        if (!bookLoans.contains(bookLoan)) throw new IllegalArgumentException("Bookloan is not found");

        bookLoan.getBook().setAvailable(true);

        bookLoans.remove(bookLoan);

    }
}
