package in.sp.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.repository.StudentRepository;

@WebServlet("/requestSeatChange")
public class RequestSeatChange extends HttpServlet {

    private StudentRepository studentRepository = new StudentRepository();

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            String email = (String) req.getSession()
                    .getAttribute("student_email");

            int requestedSeatId = Integer.parseInt(
                            req.getParameter("requestedSeatId")
                    );

            studentRepository.requestSeatChange(
                    email,
                    requestedSeatId
            );

            resp.sendRedirect("studentDashboard");
        } catch (Exception e) {
            e.printStackTrace();

            resp.sendRedirect("requestSeatChangePage");
        }
    }
}