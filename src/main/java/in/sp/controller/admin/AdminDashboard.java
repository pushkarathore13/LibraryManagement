package in.sp.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.repository.StudentRepository;

@WebServlet("/adminDashboard")
public class AdminDashboard extends HttpServlet {

	private StudentRepository studentRepository = new StudentRepository();

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			studentRepository.releaseAllExpiredSeats();
			studentRepository.endMembershipForNoSeatApprovedStudents();
			int activeMemberships = studentRepository.totalActiveMemberships();

			int selectedSeats = studentRepository.totalSelectedSeats();

			req.setAttribute("activeMemberships", activeMemberships);
			req.setAttribute("selectedSeats", selectedSeats);

			req.getRequestDispatcher("/adminDashboard.jsp")
			.forward(req, resp);

		} catch(Exception e) {

			req.getRequestDispatcher("/adminDashboard.jsp")
			.forward(req, resp);
		}
	}
}