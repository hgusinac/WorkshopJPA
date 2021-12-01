package se.WorkshopSpring.demo.Interface;

import java.util.Collection;

public interface GenericCrudDAO<T,ID> {

        T findById(ID id);
        Collection<T> findAll();
        T create(T t);
        T update(T t);
        void remove(ID id);
    }

