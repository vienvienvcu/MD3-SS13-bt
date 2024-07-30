package ra.ss13_studentmanagement.moldel.dao;

import java.util.List;

public interface IStudentDAO<T,V> extends IGenericDAO<T,V> {
    List<T> searchStudentByName(String fullName);
    boolean hasStudentsInClass(String classId);
}
