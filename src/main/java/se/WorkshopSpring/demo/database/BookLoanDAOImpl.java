package se.WorkshopSpring.demo.database;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.WorkshopSpring.demo.Interface.BookLoanDAO;
import se.WorkshopSpring.demo.Interface.GenericCrudDAO;
import se.WorkshopSpring.demo.model.BookLoan;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
@Transactional
public class BookLoanDAOImpl implements BookLoanDAO {

  private final EntityManager entityManager;

    public BookLoanDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public BookLoan findById(Integer id) {
       if (id == null) throw new IllegalArgumentException("Id was null");
      return entityManager.find(BookLoan.class,id);

    }

    @Override
    public Collection<BookLoan> findAll() {
      return entityManager.createQuery("SELECT b from BookLoan b",BookLoan.class)
               .getResultList();
    }

    @Override
    public BookLoan create(BookLoan bookLoan) {
        if (bookLoan == null) throw new IllegalArgumentException("BookLoan was null");
        entityManager.persist(bookLoan);
        return bookLoan;
    }

    @Override
    public BookLoan update(BookLoan bookLoan) {
        if (bookLoan == null) throw new IllegalArgumentException("BookLoan was null");
       return entityManager.merge(bookLoan);
    }

    @Override
    public void remove(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id was null");

    entityManager.remove(findById(id));
    }
}
