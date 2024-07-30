package ra.ss13_studentmanagement.moldel.daoIpml;

import ra.ss13_studentmanagement.moldel.dao.IClassesDAO;
import ra.ss13_studentmanagement.moldel.entity.Classes;
import ra.ss13_studentmanagement.moldel.until.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassesImpl implements IClassesDAO<Classes,String> {
    @Override
    public List<Classes> searchClassByName(String lassName) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Classes> classSearch = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call pro_searchClassByName(?)}");
            callableStatement.setString(1, lassName);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Classes classes = new Classes();
                classes.setClassId(resultSet.getString("classId"));
                classes.setClassName(resultSet.getString("className"));
                classes.setStatus(resultSet.getBoolean("status"));
                classSearch.add(classes);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }
        return classSearch;
    }

    @Override
    public List<Classes> findAll() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Classes> classesList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call pro_getAllClasses()}");
            ResultSet resultSet = callableStatement.executeQuery();
            classesList = new ArrayList<>();
            // 4 duyet resultSet day du lieu ra list
            while (resultSet.next()) {
                Classes classes = new Classes();
                classes.setClassId(resultSet.getString("classId"));
                classes.setClassName(resultSet.getString("className"));
                classes.setStatus(resultSet.getBoolean("status"));
                classesList.add(classes);
            }

        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }

        return classesList;
    }

    @Override
    public boolean save(Classes classes) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Boolean result = true;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call pro_insertClass(?,?,?)}");
//               thuc hien set gia tri cac tham so vao
            callableStatement.setString(1, classes.getClassId());
            callableStatement.setString(2, classes.getClassName());
            callableStatement.setBoolean(3, classes.getStatus());
            callableStatement.executeUpdate();

        }catch (Exception e) {
            result = false;
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }
        return result;
    }

    @Override
    public boolean update(Classes classes) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Boolean result = true;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call pro_updateClass(?,?,?)}");
            callableStatement.setString(1, classes.getClassId());
            callableStatement.setString(2, classes.getClassName());
            callableStatement.setBoolean(3, classes.getStatus());
            // Thực hiện stored procedure
            callableStatement.executeUpdate();
        }catch (Exception e) {
            result = false;
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }
        return result;
    }

    @Override
    public boolean delete(String classId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call pro_deleteClass(?)}");
            callableStatement.setString(1, classId);

            // Thực hiện stored procedure
            int rowsAffected = callableStatement.executeUpdate();
            // Nếu số hàng bị ảnh hưởng lớn hơn 0, xem như xóa thành công
            if (rowsAffected > 0) {
                result = true;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }
        return result;
    }

    @Override
    public Classes findById(String id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Classes classPro = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call pro_getClassById(?)}");
            callableStatement.setString(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            classPro = new Classes();
            while (resultSet.next()) {
                classPro.setClassId(resultSet.getString("classId"));
                classPro.setClassName(resultSet.getString("className"));
                classPro.setStatus(resultSet.getBoolean("status"));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }
        return classPro;
    }
}
