package in.sp.controller.student;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.sp.service.StudentService;

@WebServlet("/studentLogin")
public class StudentLogin extends HttpServlet {

	private static final Logger logger = LogManager.getLogger(StudentLogin.class);

	private StudentService studentService = new StudentService();

	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = req.getParameter("email");

		String password = req.getParameter("password");

		try {
			String result = studentService.login(email, password);

			if ("success".equals(result)) {

				logger.info("Student login successful: " + email);
				HttpSession session = req.getSession();

				session.setAttribute("student_email", email);

				resp.sendRedirect("studentDashboard");

			} else {

				req.setAttribute("message", result);

				RequestDispatcher rd = req.getRequestDispatcher("/studentLogin.jsp");

				rd.forward(req, resp);
			}

		} catch (Exception e) {

			logger.error("Student login error", e);

			req.setAttribute("message","Something went wrong during login");

			req.getRequestDispatcher("/studentLogin.jsp")
			.forward(req, resp);
		}
	}
}