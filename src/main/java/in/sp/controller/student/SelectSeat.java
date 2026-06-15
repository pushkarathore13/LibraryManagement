package in.sp.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.repository.StudentRepository;

@WebServlet("/selectSeat")
public class SelectSeat extends HttpServlet {

    private StudentRepository studentRepository = new StudentRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String email = (String) req.getSession()
                    .getAttribute("student_email");

            int seatId = Integer.parseInt(req.getParameter("seatId")); // get seat select by student

            studentRepository.assignSeat(email, seatId);

            resp.sendRedirect("studentDashboard");

        } catch(Exception e) {
            req.setAttribute("message", "Seat selection failed");
            req.getRequestDispatcher("/studentDashboard")
            .forward(req, resp);
        }
    }
}