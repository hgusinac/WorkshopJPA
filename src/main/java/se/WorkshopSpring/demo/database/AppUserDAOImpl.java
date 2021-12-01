package se.WorkshopSpring.demo.database;

import se.WorkshopSpring.demo.model.AppUser;

import javax.persistence.EntityManager;
import java.util.Collection;

public class AppUserDAOImpl implements appUserDAO{


    private final EntityManager entityManager;

    public AppUserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public AppUser findById(int id) {
       return entityManager.find(AppUser.class,id);
    }

    @Override
    public Collection<AppUser> findAll() {
        return entityManager.createQuery("SELECT a from AppUser a",AppUser.class)
                .getResultList();

    }

    @Override
    public AppUser create(AppUser appUser) throws IllegalAccessException {
        if (appUser==null)throw new IllegalAccessException("Appuser was null");

        entityManager.persist(appUser);

        return appUser;


    }

    @Override
    public AppUser update(AppUser appUser) throws IllegalAccessException {
        if (appUser == null) throw new IllegalAccessException("Appuser was null");
        entityManager.merge(appUser);
        return appUser;

    }

    @Override
    public void delete(int id) {
        entityManager.remove(findById(id));

    }
}
