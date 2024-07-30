package ra.ss13_studentmanagement.moldel.dao;

import java.util.List;

public interface IGenericDAO<T,V> {
    List<T> findAll();
    boolean save(T t);
    boolean update(T t);
    boolean delete(V id);
    T findById(V id);
}
