package ra.ss13_studentmanagement.moldel.serviceImpl;

import ra.ss13_studentmanagement.moldel.dao.IClassesDAO;
import ra.ss13_studentmanagement.moldel.daoIpml.ClassesImpl;
import ra.ss13_studentmanagement.moldel.entity.Classes;
import ra.ss13_studentmanagement.moldel.service.IClassesService;

import java.util.List;

public class ClassesServiceImpl implements IClassesService <Classes,String> {
    private IClassesDAO<Classes,String> classesDAO = new ClassesImpl();
    @Override
    public List<Classes> searchClassByName(String className) {
        return classesDAO.searchClassByName(className);
    }

    @Override
    public List<Classes> findAll() {
        return classesDAO.findAll();
    }

    @Override
    public boolean save(Classes classes) {
        return classesDAO.save(classes);
    }

    @Override
    public boolean update(Classes classes) {
        return classesDAO.update(classes);
    }

    @Override
    public boolean delete(String id) {
        return classesDAO.delete(id);
    }

    @Override
    public Classes findById(String id) {
        return classesDAO.findById(id);
    }
}
