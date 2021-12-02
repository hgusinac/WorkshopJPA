package se.WorkshopSpring.demo.database;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.WorkshopSpring.demo.Interface.AuthorDAO;
import se.WorkshopSpring.demo.model.Author;

import javax.persistence.EntityManager;
import java.util.Collection;


@Repository
@Transactional
public class AuthorDAOImpl implements AuthorDAO {

    private final EntityManager entityManager;

    @Autowired
    public AuthorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Author findById(Integer id) {
       if (id == null) throw new IllegalArgumentException("Id was null");
        return entityManager.find(Author.class,id);

    }

    @Override
    public Collection<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a",Author.class)
                .getResultList();
    }

    @Override
    public Author create(Author author) {
        if (author == null) throw new IllegalArgumentException("Author was null");
        entityManager.persist(author);
        return author;
    }

    @Override
    public Author update(Author author) {
       if (author == null) throw new IllegalArgumentException("Author was null");
       return entityManager.merge(author);
    }

    @Override
    public void remove(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id was null");

        entityManager.remove(findById(id));

    }
}
