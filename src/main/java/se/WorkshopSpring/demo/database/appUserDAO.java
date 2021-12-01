package se.WorkshopSpring.demo.database;

import se.WorkshopSpring.demo.model.AppUser;

import java.util.Collection;

public interface appUserDAO {
    AppUser findById(int id);
    Collection<AppUser> findAll();
    AppUser create(AppUser appUser) throws IllegalAccessException;
    AppUser update(AppUser appUser) throws IllegalAccessException;
    void delete (int id);
}
