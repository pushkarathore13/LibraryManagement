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
			int activeMemberships = studentRepository.totalActiveMemberships();

			req.setAttribute( "activeMemberships", activeMemberships);

			req.getRequestDispatcher("/adminDashboard.jsp")
			.forward(req, resp);

		} catch(Exception e) {
			req.getRequestDispatcher("/adminDashboard.jsp")
			.forward(req, resp);
		}
	}
}