package in.sp.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.model.Student;
import in.sp.repository.StudentRepository;

@WebServlet("/endMembershipPage")
public class EndMembershipPage extends HttpServlet {

	private StudentRepository studentRepository =
			new StudentRepository();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			List<Student> activeMembers =
					studentRepository.activeMembers();

			req.setAttribute("activeMembers", activeMembers);

			req.getRequestDispatcher("/endMembership.jsp")
			.forward(req, resp);

		} catch(Exception e) {
			resp.sendRedirect("adminDashboard");
		}
	}
}