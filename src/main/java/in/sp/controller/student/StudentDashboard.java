package in.sp.controller.student;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.model.Seat;
import in.sp.model.Student;
import in.sp.repository.SeatRepository;
import in.sp.repository.StudentRepository;

@WebServlet("/studentDashboard")
public class StudentDashboard extends HttpServlet {

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

			boolean activeMember = false;
			boolean renewAllowed = false;

			if(student.getMembershipEnd() != null) {

				LocalDate endDate = student.getMembershipEnd().toLocalDate();

				LocalDate today = LocalDate.now();

				if(!endDate.isBefore(today)) {
					activeMember = true;
				}

				long daysAfterExpiry = ChronoUnit.DAYS.between(endDate, today);

				if(daysAfterExpiry >= 0 && daysAfterExpiry <= 5) {
					renewAllowed = true;
				}

				if(daysAfterExpiry > 5) {
					studentRepository.releaseExpiredSeat(email);

					student = studentRepository.findByEmail(email);

					activeMember = false;
					renewAllowed = false;
				}
			}

			List<Seat> availableSeats = null;

			if(!activeMember || student.getSeatId() == 0) {
				availableSeats = seatRepository.allSeats();
			}

			boolean seatChangePending = studentRepository
					.hasPendingSeatChangeRequest(email);

			req.setAttribute("student", student);
			req.setAttribute("activeMember", activeMember);
			req.setAttribute("renewAllowed", renewAllowed);
			req.setAttribute("availableSeats", availableSeats);
			req.setAttribute("seatChangePending", seatChangePending);

			req.getRequestDispatcher("/studentDashboard.jsp")
			.forward(req, resp);

		} catch(Exception e) {

			req.setAttribute("message", "Dashboard loading failed");

			req.getRequestDispatcher("/login.jsp")
			.forward(req, resp);
		}
	}
}