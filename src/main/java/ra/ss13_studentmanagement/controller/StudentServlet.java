package ra.ss13_studentmanagement.controller;

import ra.ss13_studentmanagement.moldel.entity.Classes;
import ra.ss13_studentmanagement.moldel.entity.Student;
import ra.ss13_studentmanagement.moldel.service.IClassesService;
import ra.ss13_studentmanagement.moldel.service.IStudentService;
import ra.ss13_studentmanagement.moldel.serviceImpl.ClassesServiceImpl;
import ra.ss13_studentmanagement.moldel.serviceImpl.StudentServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/StudentServlet")
public class StudentServlet extends HttpServlet {
    private IStudentService<Student,Integer> studentService = new StudentServiceImpl();
    private IClassesService<Classes,String> classesService = new ClassesServiceImpl();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
           request.setCharacterEncoding("UTF-8");
           response.setCharacterEncoding("UTF-8");
           String action = request.getParameter("action");
           if (action == null) {
               action = "";
           }
           switch (action) {
               case "add":
                   formCreateStudent(request,response);
                   break;
               case "edit":
                   formEditStudent(request,response);
                   break;
               case "delete":
                   deleteStudent(request,response);
                   break;
               case "search":
                   searchStudents(request,response);
                   break;
               default:
                   listStudents(request,response);
                   break;
           }


    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                createStudent(request,response);
                break;
            case "edit":
                editStudent(request,response);
                break;
            default:
                listStudents(request,response);
                break;
        }

    }
    public void listStudents(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Student> studentList = studentService.findAll();
        request.setAttribute("students", studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/student/studentList.jsp");
        dispatcher.forward(request,response);
    }
    public void formCreateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/student/studentCreateForm.jsp");
        request.setAttribute("classList", classesService.findAll());
        dispatcher.forward(request,response);
    }
    public void createStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
         String fullName = request.getParameter("fullName");
         String email = request.getParameter("email");
         String address = request.getParameter("address");
         String phone = request.getParameter("phone");
         Boolean status = Boolean.valueOf(request.getParameter("status"));
         String classId = request.getParameter("classId");
        // Tạo đối tượng User và lưu vào cơ sở dữ liệu
         Student newStudent = new Student();
         newStudent.setFullName(fullName);
         newStudent.setEmail(email);
         newStudent.setAddress(address);
         newStudent.setPhone(phone);
         newStudent.setStatus(status);
         newStudent.setClassId(classId);
         Boolean result = studentService.save(newStudent);
         if (result) {

             listStudents(request,response);
         }else {
             request.setAttribute("errorMessage", "Failed to add user.");
             try {
                 request.getRequestDispatcher("views/student/studentCreateForm.jsp").forward(request, response);
             } catch (ServletException e) {
                 throw new RuntimeException(e);
             }
         }



    }
    public void formEditStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.findById(studentId);
        request.setAttribute("studentUpdate", student);
        request.setAttribute("classList", classesService.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/student/studentEditForm.jsp");
        dispatcher.forward(request,response);
    }
    public void editStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        String classId = request.getParameter("classId");
        Student studentUpdate = studentService.findById(studentId);
        if (studentUpdate == null){
            request.setAttribute("errorMessage", "Student not found.");
        }else {
            studentUpdate.setFullName(fullName);
            studentUpdate.setEmail(email);
            studentUpdate.setAddress(address);
            studentUpdate.setPhone(phone);
            studentUpdate.setStatus(status);
            studentUpdate.setClassId(classId);
            Boolean result = studentService.update(studentUpdate);
            if (result) {
                listStudents(request,response);
            }else {
                request.setAttribute("errorMessage", "Failed to update user.");
                request.getRequestDispatcher("views/student/studentEditForm.jsp").forward(request, response);
            }
        }
    }
    public void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        Student studentDelete = studentService.findById(studentId);
        if (studentDelete == null){
            request.setAttribute("errorMessage", "Student not found.");
        }else {
            studentService.delete(studentId);
            request.setAttribute("errorMessage", "Student deleted successfully.");
        }
        listStudents(request,response);
    }
    public void searchStudents(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fullName = request.getParameter("fullName");
        List<Student> studentSearch;
        studentSearch = studentService.searchStudentByName(fullName);
        if (studentSearch.isEmpty()) {
            request.setAttribute("error", "User not found.");
        } else {
            request.setAttribute("students", studentSearch);
        }
        request.getRequestDispatcher("views/student/studentList.jsp").forward(request, response);
    }

    public void destroy() {
    }
}