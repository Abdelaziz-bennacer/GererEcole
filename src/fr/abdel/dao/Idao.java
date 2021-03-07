package fr.abdel.dao;

import java.util.List;

public interface Idao<T> {

    boolean create(T o);
    boolean delete(T o);
    boolean update(T o);
    T findById(int id);
    List<T> findAll();
}
