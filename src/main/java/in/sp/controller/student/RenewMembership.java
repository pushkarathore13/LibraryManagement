package in.sp.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.repository.StudentRepository;

@WebServlet("/renewMembership")
public class RenewMembership extends HttpServlet {

    private StudentRepository studentRepository = new StudentRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String email = (String) req.getSession()
                    .getAttribute("student_email");

            studentRepository.renewMembership(email);

            resp.sendRedirect("studentDashboard");

        } catch(Exception e) {
            req.setAttribute("message", "Renew failed");
            req.getRequestDispatcher("/studentDashboard")
            .forward(req, resp);
        }
    }
}