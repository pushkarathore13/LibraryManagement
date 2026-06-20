package in.sp.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.model.Student;
import in.sp.repository.StudentRepository;

@WebServlet("/endingSoonMembers")
public class EndingSoonMembers extends HttpServlet {

	private StudentRepository studentRepository = new StudentRepository();

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			List<Student> students = studentRepository.endingSoonMembers();

			req.setAttribute("students", students);

			req.getRequestDispatcher("/endingSoonMembers.jsp")
			.forward(req, resp);

		} catch(Exception e) {

			req.setAttribute("message", "Unable to load ending soon members");

			req.getRequestDispatcher("/adminDashboard")
			.forward(req, resp);
		}
	}
}