package in.sp.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.model.Student;
import in.sp.repository.StudentRepository;

@WebServlet("/requestSeatChangePage")
public class RequestSeatChangePage extends HttpServlet {

	private StudentRepository studentRepository = new StudentRepository();

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			String email = (String) req.getSession()
					.getAttribute("student_email");

			Student student = studentRepository.findByEmail(email);

			req.setAttribute("student", student);

			req.getRequestDispatcher("/requestSeatChange.jsp")
			.forward(req, resp);

		} catch(Exception e) {

			resp.sendRedirect("studentDashboard");
		}
	}
}