package se.WorkshopSpring.demo.database;

import se.WorkshopSpring.demo.model.Details;

import java.util.Collection;

public interface detailsDAO {
    Details findById(int id) throws IllegalAccessException;
    Collection<Details> findAll();
    Details create(Details details) throws IllegalAccessException;
    Details update (Details details) throws IllegalAccessException;
    void delete(int id) throws IllegalAccessException;
}
