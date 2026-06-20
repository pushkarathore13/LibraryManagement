package in.sp.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.model.Student;
import in.sp.repository.StudentRepository;

@WebServlet("/expiredMembers")
public class ExpiredMembers extends HttpServlet {

	private StudentRepository studentRepository =
			new StudentRepository();

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			List<Student> students = studentRepository.expiredMembers();

			req.setAttribute("students", students);

			req.getRequestDispatcher("/expiredMembers.jsp")
			.forward(req, resp);

		} catch(Exception e) {

			req.setAttribute("message", "Unable to load expired members");

			req.getRequestDispatcher("/adminDashboard")
			.forward(req, resp);
		}
	}
}