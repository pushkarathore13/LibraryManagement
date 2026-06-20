package in.sp.controller.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.model.Seat;
import in.sp.model.Student;
import in.sp.repository.SeatRepository;
import in.sp.repository.StudentRepository;

@WebServlet("/requestSeatChangePage")
public class RequestSeatChangePage extends HttpServlet {

	private StudentRepository studentRepository = new StudentRepository();

	private SeatRepository seatRepository = new SeatRepository();

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			String email = (String) req.getSession()
					.getAttribute("student_email");

			Student student = studentRepository.findByEmail(email);

			List<Seat> seats = seatRepository.allSeats();

			req.setAttribute("student", student);
			req.setAttribute("seats", seats);

			req.getRequestDispatcher("/requestSeatChange.jsp")
			.forward(req, resp);

		} catch(Exception e) {

			resp.sendRedirect("studentDashboard");
		}
	}
}