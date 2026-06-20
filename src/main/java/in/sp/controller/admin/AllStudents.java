package in.sp.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.model.Student;
import in.sp.repository.StudentRepository;

@WebServlet("/allStudents")
public class AllStudents extends HttpServlet {

	private StudentRepository studentRepository =new StudentRepository();

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			List<Student> students =
					studentRepository.allStudents();

			req.setAttribute("students", students);

			req.getRequestDispatcher("/allStudents.jsp")
			.forward(req, resp);

		} catch(Exception e) {

			req.setAttribute("message", "Unable to load students");

			req.getRequestDispatcher("/adminDashboard")
			.forward(req, resp);
		}
	}
}