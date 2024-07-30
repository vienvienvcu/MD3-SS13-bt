package ra.ss13_studentmanagement.moldel.service;

import java.util.List;

public interface IClassesService <T,V> extends IGenericService<T,V> {
    List<T> searchClassByName(String userName);
}
