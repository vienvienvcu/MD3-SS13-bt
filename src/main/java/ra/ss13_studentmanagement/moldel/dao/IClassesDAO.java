package ra.ss13_studentmanagement.moldel.dao;

import java.util.List;

public interface IClassesDAO <T,V> extends IGenericDAO<T, V> {
    List<T> searchClassByName(String className);
}
