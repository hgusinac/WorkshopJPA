package se.WorkshopSpring.demo.database;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.WorkshopSpring.demo.Interface.BookDAO;
import se.WorkshopSpring.demo.Interface.GenericCrudDAO;
import se.WorkshopSpring.demo.model.Book;

import javax.persistence.EntityManager;
import java.util.Collection;


@Repository
@Transactional
public class BookDAOImpl implements BookDAO {


    private final EntityManager entityManager;

    public BookDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Book findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id was null");
       return entityManager.find(Book.class,id);
    }

    @Override
    public Collection<Book> findAll() {
        return entityManager.createQuery("SELECT b from Book b",Book.class)
                .getResultList();
    }

    @Override
    public Book create(Book book) {
        if (book == null) throw new IllegalArgumentException("Book was null");

         entityManager.persist(book);
         return book;
    }

    @Override
    public Book update(Book book) {
        if (book == null) throw new IllegalArgumentException("Book was null");

        return entityManager.merge(book);
    }

    @Override
    public void remove(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id was null");

        entityManager.remove(findById(id));

    }
}
