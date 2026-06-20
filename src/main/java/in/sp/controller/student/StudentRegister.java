package in.sp.controller.student;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.sp.model.Student;
import in.sp.service.StudentService;

@WebServlet("/studentRegister")
public class StudentRegister extends HttpServlet {

	private static final Logger logger = LogManager.getLogger(StudentRegister.class);

	private StudentService studentService = new StudentService();

	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {

		String password = req.getParameter("password");

		String confirmPassword = req.getParameter("confirmPassword");

		if(password == null ||
				confirmPassword == null ||
				!password.equals(confirmPassword)) {

			req.setAttribute(
					"message",
					"Password and Confirm Password do not match"
			);

			req.getRequestDispatcher("/studentRegister.jsp")
			.forward(req, resp);

			return;
		}

		Student student =
				new Student();

		student.setName(req.getParameter("name"));
		student.setEmail(req.getParameter("email"));
		student.setMobile(req.getParameter("mobile"));
		student.setGender(req.getParameter("gender"));
		student.setOccupation(req.getParameter("occupation"));
		student.setAadhaarId(req.getParameter("aadhaar_id"));
		student.setPassword(password);

		try {
			String result = studentService.register(student);

			if ("success".equals(result)) {

				logger.info( "Student registration request sent: " + student.getEmail() );

				req.setAttribute("message", "Registration request sent to admin. Please wait for approval.");

				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");

				rd.forward(req, resp);

			} else {

				req.setAttribute("message", result);
				req.setAttribute("student", student);

				RequestDispatcher rd = req.getRequestDispatcher("/studentRegister.jsp");

				rd.forward(req, resp);
			}

		} catch (Exception e) {

			logger.error("Student registration error", e);

			req.setAttribute("message", "Something went wrong during registration");

			req.setAttribute("student", student);

			req.getRequestDispatcher("/studentRegister.jsp")
			.forward(req, resp);
		}
	}
}