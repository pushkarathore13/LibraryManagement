package in.sp.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.repository.StudentRepository;

@WebServlet("/selectSeat")
public class SelectSeat extends HttpServlet {

	private StudentRepository studentRepository =
			new StudentRepository();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			String email =
					(String) req.getSession().getAttribute("student_email");

			int seatId =
					Integer.parseInt(req.getParameter("seatId"));

			String shiftName =
					req.getParameter("shiftName");

			if(shiftName == null || shiftName.trim().isEmpty()) {
				req.setAttribute("message", "Please select shift");
				req.getRequestDispatcher("/studentDashboard").forward(req, resp);
				return;
			}

			studentRepository.assignSeat(email, seatId, shiftName);

			resp.sendRedirect("studentDashboard");

		} catch(Exception e) {
			req.setAttribute("message", "Seat selection failed");
			req.getRequestDispatcher("/studentDashboard").forward(req, resp);
		}
	}
}