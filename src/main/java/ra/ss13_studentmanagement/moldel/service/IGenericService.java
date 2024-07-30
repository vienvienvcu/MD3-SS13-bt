package ra.ss13_studentmanagement.moldel.service;

import java.util.List;

public interface IGenericService<T,V> {
    List<T> findAll();
    boolean save(T t);
    boolean update(T t);
    boolean delete(V id);
    T findById(V id);
}
