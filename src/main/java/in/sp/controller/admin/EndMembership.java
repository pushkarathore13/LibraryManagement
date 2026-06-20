package in.sp.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.repository.StudentRepository;

@WebServlet("/endMembership")
public class EndMembership extends HttpServlet {

	private StudentRepository studentRepository =
			new StudentRepository();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			int studentId =
					Integer.parseInt(req.getParameter("studentId"));

			String reason =
					req.getParameter("reason");

			studentRepository.endMembershipByAdmin(studentId, reason);

			resp.sendRedirect("adminDashboard");

		} catch(Exception e) {
			req.setAttribute("message", "Membership end failed");
			req.getRequestDispatcher("/endMembership.jsp").forward(req, resp);
		}
	}
}