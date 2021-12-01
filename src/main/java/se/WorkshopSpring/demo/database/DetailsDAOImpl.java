package se.WorkshopSpring.demo.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.WorkshopSpring.demo.model.Details;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
@Transactional
public class DetailsDAOImpl implements GenericCrudDAO<Details,Integer>{

    private final EntityManager entityManager;

    public DetailsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }




    @Override
    public Details findById(Integer id) {
        if (id==null) throw new IllegalArgumentException("Id was null");
        return entityManager.find(Details.class,id);
    }

    @Override
    public Collection<Details> findAll() {
        return entityManager.createQuery("SELECT d FROM Details d",Details.class)
                .getResultList();
    }

    @Override
    public Details create(Details details) {
        if (details==null) throw new IllegalArgumentException("Details was null");
        entityManager.persist(details);
        return details;
    }

    @Override
    public Details update(Details details) {
        if(details == null) throw new IllegalArgumentException("Details was null");
       return entityManager.merge(details);
    }

    @Override
    public void remove(Integer id) {
        if(id == null) throw new IllegalArgumentException("Id was null");

        entityManager.remove(findById(id));

    }
}
