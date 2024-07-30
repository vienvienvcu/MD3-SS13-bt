package ra.ss13_studentmanagement.moldel.service;

import java.util.List;

public interface IStudentService<T,V> extends IGenericService<T,V> {
    List<T> searchStudentByName(String userName);
    boolean hasStudentsInClass(String classId);
}
