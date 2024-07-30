package ra.ss13_studentmanagement.moldel.serviceImpl;

import ra.ss13_studentmanagement.moldel.dao.IStudentDAO;
import ra.ss13_studentmanagement.moldel.daoIpml.StudentImpl;
import ra.ss13_studentmanagement.moldel.entity.Student;
import ra.ss13_studentmanagement.moldel.service.IStudentService;

import java.util.List;

public class StudentServiceImpl implements IStudentService<Student,Integer> {
    private IStudentDAO<Student,Integer> studentDAO = new StudentImpl();
    @Override
    public List<Student> searchStudentByName(String fullName) {
        return studentDAO.searchStudentByName(fullName);
    }

    @Override
    public boolean hasStudentsInClass(String classId) {
        return studentDAO.hasStudentsInClass(classId);
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public boolean save(Student student) {
        return studentDAO.save(student);
    }

    @Override
    public boolean update(Student student) {
        return studentDAO.update(student);
    }

    @Override
    public boolean delete(Integer id) {
        return studentDAO.delete(id);
    }

    @Override
    public Student findById(Integer id) {
        return studentDAO.findById(id);
    }
}
