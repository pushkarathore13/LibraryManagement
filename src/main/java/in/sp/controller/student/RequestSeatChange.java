package in.sp.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.repository.StudentRepository;

@WebServlet("/requestSeatChange")
public class RequestSeatChange extends HttpServlet {

	private StudentRepository studentRepository =
			new StudentRepository();

	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			String email = (String) req.getSession()
					.getAttribute("student_email");

			int requestedSeatId = Integer.parseInt( req.getParameter("requestedSeatId"));

			boolean requested = studentRepository.requestSeatChange( email,requestedSeatId);

			if(requested) {
				resp.sendRedirect("studentDashboard");
			} else {
				req.setAttribute(
						"message",
						"Invalid request. Seat may already be occupied or request already pending."
				);

				req.getRequestDispatcher("/requestSeatChangePage")
				.forward(req, resp);
			}

		} catch(Exception e) {

			resp.sendRedirect("requestSeatChangePage");
		}
	}
}