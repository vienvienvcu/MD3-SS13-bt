package ra.ss13_studentmanagement.moldel.daoIpml;

import ra.ss13_studentmanagement.moldel.dao.IStudentDAO;
import ra.ss13_studentmanagement.moldel.entity.Student;
import ra.ss13_studentmanagement.moldel.until.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentImpl implements IStudentDAO<Student,Integer>{
    @Override
    public List<Student> searchStudentByName(String fullName) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Student> studentSearch = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call pro_searchStudentByName(?)}");
            callableStatement.setString(1, fullName);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt(1));
                student.setFullName(resultSet.getString("fullName"));
                student.setEmail(resultSet.getString("email"));
                student.setAddress(resultSet.getString("address"));
                student.setPhone(resultSet.getString("phone"));
                student.setStatus(resultSet.getBoolean("status"));
                student.setClassId(resultSet.getString("classId"));
                studentSearch.add(student);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }

        return studentSearch;
    }

    @Override
    public boolean hasStudentsInClass(String classId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean hasStudents = false;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call pro_hasStudentsInClass(?)}");
            callableStatement.setString(1, classId);
            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                int studentCount = resultSet.getInt("studentCount");
                hasStudents = (studentCount > 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return hasStudents;
    }

    @Override
    public List<Student> findAll() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Student> studentList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call pro_getAllStudent()}");
            ResultSet resultSet = callableStatement.executeQuery();
            studentList = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setFullName(resultSet.getString("fullName"));
                student.setEmail(resultSet.getString("email"));
                student.setAddress(resultSet.getString("address"));
                student.setPhone(resultSet.getString("phone"));
                student.setStatus(resultSet.getBoolean("status"));
                student.setClassId(resultSet.getString("classId"));
                studentList.add(student);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return studentList;
    }

    @Override
    public boolean save(Student student) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Boolean result = true;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement =connection.prepareCall("{call pro_insertStudent(?,?,?,?,?,?)}");
            callableStatement.setString(1,student.getFullName());
            callableStatement.setString(2,student.getEmail());
            callableStatement.setString(3,student.getAddress());
            callableStatement.setString(4,student.getPhone());
            callableStatement.setBoolean(5,student.getStatus());
            callableStatement.setString(6,student.getClassId());
            callableStatement.executeUpdate();

        }catch (Exception e) {
            result = false;
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return result;
    }

    @Override
    public boolean update(Student student) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean result = true;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement =connection.prepareCall("{call pro_updateStudent(?,?,?,?,?,?,?)}");
            callableStatement.setInt(1,student.getId());
            callableStatement.setString(2,student.getFullName());
            callableStatement.setString(3,student.getEmail());
            callableStatement.setString(4,student.getAddress());
            callableStatement.setString(5,student.getPhone());
            callableStatement.setBoolean(6,student.getStatus());
            callableStatement.setString(7,student.getClassId());
            // Thực hiện stored procedure
            callableStatement.executeUpdate();
        }catch (Exception e) {
            result = false;
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call pro_deleteStudent(?)}");
            callableStatement.setInt(1,id);
            // Thực hiện stored procedure
            int rowsAffected = callableStatement.executeUpdate();

            // Nếu số hàng bị ảnh hưởng lớn hơn 0, xem như xóa thành công
            if (rowsAffected > 0) {
                result = true;
            }

        }catch (Exception e){

            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }
        return result;
    }

    @Override
    public Student findById(Integer id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Student studentInfor = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call pro_getStudentById(?)}");
            callableStatement.setInt(1,id);
            ResultSet resultSet = callableStatement.executeQuery(); //
            studentInfor = new Student();
            //   Nếu có dữ liệu trong ResultSet, gán dữ liệu vào đối tượng Student
            if (resultSet.next()){
                studentInfor.setId(resultSet.getInt("id"));
                studentInfor.setFullName(resultSet.getString("fullName"));
                studentInfor.setEmail(resultSet.getString("email"));
                studentInfor.setAddress(resultSet.getString("address"));
                studentInfor.setPhone(resultSet.getString("phone"));
                studentInfor.setStatus(resultSet.getBoolean("status"));
                studentInfor.setClassId(resultSet.getString("classId"));

            }

        }catch (Exception e){
            e.printStackTrace();;
        }


        return studentInfor;
    }
}
