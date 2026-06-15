package in.sp.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.model.Student;
import in.sp.repository.StudentRepository;
import in.sp.service.AdminService;
import in.sp.service.StudentService;
import in.sp.util.RegexPatterns;

@WebServlet("/login")
public class Login extends HttpServlet {

	private AdminService adminService =
			new AdminService();

	private StudentService studentService =
			new StudentService();

	private StudentRepository studentRepository =
			new StudentRepository();

	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {

		String email =
				req.getParameter("email");

		String password =
				req.getParameter("password");

		if(email == null || email.trim().isEmpty()) {
			req.setAttribute("message", "Email cannot be empty");
			req.getRequestDispatcher("/login.jsp")
			.forward(req, resp);
			return;
		}

		if(!Pattern.matches(RegexPatterns.EMAIL_REGEX, email)) {
			req.setAttribute("message", "Invalid email format");
			req.getRequestDispatcher("/login.jsp")
			.forward(req, resp);
			return;
		}

		if(password == null || password.trim().isEmpty()) {
			req.setAttribute("message", "Password cannot be empty");
			req.getRequestDispatcher("/login.jsp")
			.forward(req, resp);
			return;
		}

		try {
			boolean adminValid =
					adminService.login(email, password);

			if(adminValid) {

				HttpSession session =
						req.getSession();

				session.setAttribute("admin_email", email);

				resp.sendRedirect("adminDashboard");
				return;
			}

			Student student =
					studentRepository.findByEmail(email);

			if(student != null &&
					"REJECTED".equalsIgnoreCase(student.getStatus())) {

				req.setAttribute(
						"message",
						"Your request was rejected. Reason: "
						+ student.getRejectionReason()
						+ ". Please correct and submit again."
				);

				req.setAttribute("student", student);

				req.getRequestDispatcher("/studentRegister.jsp")
				.forward(req, resp);
				return;
			}

			String studentResult =
					studentService.login(email, password);

			if("success".equals(studentResult)) {

				HttpSession session =
						req.getSession();

				session.setAttribute("student_email", email);

				resp.sendRedirect("studentDashboard");
				return;

			} else {

				req.setAttribute("message", studentResult);

				req.getRequestDispatcher("/login.jsp")
				.forward(req, resp);
			}

		} catch(Exception e) {

			e.printStackTrace();

			req.setAttribute(
					"message",
					"Something went wrong during login"
			);

			req.getRequestDispatcher("/login.jsp")
			.forward(req, resp);
		}
	}
}