package in.sp.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.model.SeatChangeRequest;
import in.sp.repository.StudentRepository;

@WebServlet("/seatChangeRequests")
public class SeatChangeRequests extends HttpServlet {

	private StudentRepository studentRepository = new StudentRepository();

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			List<SeatChangeRequest> requests = studentRepository.pendingSeatChangeRequests();

			req.setAttribute("seatChangeRequests",requests);

			req.getRequestDispatcher("/seatChangeRequests.jsp")
			.forward(req, resp);

		} catch(Exception e) {

			req.setAttribute(
					"message",
					"Unable to load seat change requests"
			);
			req.getRequestDispatcher("/adminDashboard")
			.forward(req, resp);
		}
	}
}