package in.sp.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.repository.StudentRepository;

@WebServlet("/rejectSeatChange")
public class RejectSeatChange extends HttpServlet {

	private StudentRepository studentRepository = new StudentRepository();

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			int requestId = Integer.parseInt(req.getParameter("requestId")
					);

			studentRepository
			.rejectSeatChangeRequest(requestId);

			resp.sendRedirect("adminDashboard");

		} catch(Exception e) {
			resp.sendRedirect("adminDashboard");
		}
	}
}