package se.WorkshopSpring.demo.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.WorkshopSpring.demo.model.Details;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
@Transactional
public class DetailsDAOImpl implements detailsDAO{

    private final EntityManager entityManager;

    @Autowired
    public DetailsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Details create(Details details) throws IllegalAccessException {
        if (details == null) throw new IllegalAccessException("Details was null");

            entityManager.persist(details);

        return details;
    }

    @Override
    public Details update(Details details) throws IllegalAccessException {
        if(details == null) throw new IllegalAccessException("Details was null");
        return entityManager.merge(details);
    }


    @Override
    public Details findById(int id) throws IllegalAccessException {
       if(id <=0 ) throw new IllegalAccessException("Id cant be 0");
       return entityManager.find(Details.class,id);
    }

    @Override
    public Collection<Details> findAll() {
        return entityManager.createQuery("select d from Details d ",Details.class)
                .getResultList();
    }



    @Override

    public void delete(int id) throws IllegalAccessException {

      entityManager.remove(findById(id));

    }
}
