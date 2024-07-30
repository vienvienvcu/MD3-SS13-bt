package ra.ss13_studentmanagement.controller;

import ra.ss13_studentmanagement.moldel.entity.Classes;
import ra.ss13_studentmanagement.moldel.entity.Student;
import ra.ss13_studentmanagement.moldel.service.IClassesService;
import ra.ss13_studentmanagement.moldel.service.IStudentService;
import ra.ss13_studentmanagement.moldel.serviceImpl.ClassesServiceImpl;
import ra.ss13_studentmanagement.moldel.serviceImpl.StudentServiceImpl;

import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ClassServlet", value = "/ClassServlet")
public class ClassServlet extends HttpServlet {
    private IClassesService<Classes,String> classService = new ClassesServiceImpl();
    private IStudentService<Student,Integer> studentService = new StudentServiceImpl();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
              request.setCharacterEncoding("UTF-8");
              response.setCharacterEncoding("UTF-8");
              String action = request.getParameter("action");
              if (action == null) {
                  action = "";
              }
              switch (action) {
                  case "add":
                    formCreateClasses(request,response);
                      break;
                  case "edit":
                      formCreateEdit(request,response);
                      break;
                  case "delete":
                      deleteClass(request,response);
                      break;
                  case "search":
                      searchClass(request,response);
                      break;
                  default:
                      listClasses(request, response);
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
                newClass(request,response);
                break;
            case "edit":
                editClass(request,response);
                break;
            default:
                listClasses(request,response);
                break;
        }
    }


    private void listClasses(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // goi getAll cua service
        List<Classes> classesList = classService.findAll();
        request.setAttribute("classes", classesList);
        try {
            request.getRequestDispatcher("views/classes/classList.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }
    private void formCreateClasses(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/classes/classCreateForm.jsp");
        dispatcher.forward(request,response);
    }
    private void newClass(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String classId = request.getParameter("classId");
        String className = request.getParameter("className");
        Boolean status = Boolean.valueOf(request.getParameter("status"));
        Classes classes = new Classes();
        classes.setClassId(classId);
        classes.setClassName(className);
        classes.setStatus(status);
        boolean result = classService.save(classes);
        if (result) {
            listClasses(request, response);
        }else {
            request.setAttribute("errorMessage", "Failed to add user.");
            try {
                request.getRequestDispatcher("views/classes/classCreateForm.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void formCreateEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String classId = request.getParameter("classId");
        Classes existingClass = classService.findById(classId);
        request.setAttribute("classUpdate", existingClass);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/classes/classUpdateForm.jsp");
        dispatcher.forward(request,response);

    }
    private void editClass(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String classId = request.getParameter("classId");
        String className = request.getParameter("className");
        boolean status = Boolean.valueOf(request.getParameter("status"));
        Classes classUpdate = new Classes();
        if (classId != null) {
            classUpdate.setClassId(classId);
            classUpdate.setClassName(className);
            classUpdate.setStatus(status);
            boolean result = classService.update(classUpdate);
            if (result) {
                listClasses(request, response);
            }else {
                request.setAttribute("errorMessage", "Failed to update user.");
                request.getRequestDispatcher("views/classes/classUpdateForm.jsp").forward(request, response);
            }
        }else {
            request.setAttribute("errorMessage", "ClassId is required.");
            request.getRequestDispatcher("views/classes/classList.jsp").forward(request, response);
        }
    }
    private void deleteClass(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("deleteClass");
        String classId = request.getParameter("classId");

        // Kiểm tra lớp học có học sinh không
        boolean hasStudents = studentService.hasStudentsInClass(classId);

        if (hasStudents) {
            // Nếu có học sinh, hiển thị thông báo lỗi
            request.setAttribute("error", "Class cannot be delete because it has students.");
        } else {
            // Nếu không có học sinh, xóa lớp học
             classService.delete(classId);
             request.setAttribute("message", "Class deleted successfully.");

        }
        request.setAttribute("classes", classService.findAll());
        request.getRequestDispatcher("views/classes/classList.jsp").forward(request, response);

    }

    private void searchClass(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String className = request.getParameter("className");
        List<Classes> classSearch;
        classSearch = classService.searchClassByName(className);
        if (classSearch.isEmpty()) {
            request.setAttribute("error", "Class not found.");
        } else {
            request.setAttribute("classes", classSearch);
        }
        request.getRequestDispatcher("views/classes/classList.jsp").forward(request, response);
    }
    public void destroy() {
    }
}