package in.sp.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.sp.model.Student;
import in.sp.repository.StudentRepository;

@WebServlet("/pendingStudents")
public class PendingStudents extends HttpServlet {

    private StudentRepository studentRepository = new StudentRepository();

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            List<Student> students = studentRepository.pendingStudents();

            req.setAttribute("students", students);

            req.getRequestDispatcher("/pendingStudents.jsp")
            .forward(req, resp);

        } catch (Exception e) {

            req.setAttribute("message", "Unable to load pending students" );

            req.getRequestDispatcher("/adminDashboard.jsp").forward(req, resp);
        }
    }
}