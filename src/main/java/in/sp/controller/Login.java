package in.sp.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.service.AdminService;
import in.sp.service.StudentService;
import in.sp.util.RegexPatterns;

@WebServlet("/login")
public class Login extends HttpServlet {

    private AdminService adminService = new AdminService(); // admin login 

    private StudentService studentService = new StudentService(); // student login

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(email == null || email.trim().isEmpty()) {
            req.setAttribute("message", "Email cannot be empty");
            req.getRequestDispatcher("/login.jsp")
            .forward(req, resp);
            return;
        }

        if(!Pattern.matches(RegexPatterns.EMAIL_REGEX, email)) {
            req.setAttribute("message", "Invalid email format");
            req.getRequestDispatcher("/login.jsp")
            .forward(req, resp);
            return;
        }

        if(password == null || password.trim().isEmpty()) {
            req.setAttribute("message", "Password cannot be empty");
            req.getRequestDispatcher("/login.jsp")
            .forward(req, resp);
            return;
        }

        try {
            boolean adminValid = adminService.login(email, password); // first check info entered belongs to admin
            if(adminValid) {
                HttpSession session = req.getSession();
                session.setAttribute("admin_email", email);
                resp.sendRedirect("adminDashboard");
                return;
            }

            String studentResult = studentService.login(email, password); // if admin login fails, check student login

            if("success".equals(studentResult)) {
                HttpSession session = req.getSession();
                session.setAttribute("student_email", email);
                resp.sendRedirect("studentDashboard");
                return;
            } else {

                req.setAttribute("message", studentResult);
                req.getRequestDispatcher("/login.jsp")
                .forward(req, resp);
            }
        } catch(Exception e) {
            req.setAttribute("message","Something went wrong during login"); 
            req.getRequestDispatcher("/login.jsp")
            .forward(req, resp);
        }
    }
}