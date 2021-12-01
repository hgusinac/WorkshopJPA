package se.WorkshopSpring.demo.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.WorkshopSpring.demo.model.AppUser;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
@Transactional
public class AppUserDAOImpl implements GenericCrudDAO <AppUser,Integer> {

    private final EntityManager entityManager;

    @Autowired
    public AppUserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public AppUser findById(Integer id) {
       if(id == null) throw new IllegalArgumentException("Id was null");
      return entityManager.find(AppUser.class,id);

    }

    @Override
    public Collection<AppUser> findAll() {
        return entityManager.createQuery("select a FROM AppUser a",AppUser.class)
                .getResultList();
    }

    @Override
    public AppUser create(AppUser appUser) {
        if(appUser == null) throw new IllegalArgumentException("AppUser was null");

        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    public AppUser update(AppUser appUser) {
        if(appUser == null) throw new IllegalArgumentException("AppUser was null");

        return entityManager.merge(appUser);
    }

    @Override
    public void remove(Integer id) {
        if(id == null) throw new IllegalArgumentException("Id was null");

        entityManager.remove(findById(id));

    }
}
