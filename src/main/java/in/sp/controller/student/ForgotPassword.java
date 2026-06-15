package in.sp.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.sp.service.StudentService;

@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {

	private StudentService studentService = new StudentService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			String result = studentService.forgotPassword(req.getParameter("email"),req.getParameter("newPassword"));

			if ("success".equals(result)) {

				req.setAttribute("message","Password reset successfully. Please login.");

				req.getRequestDispatcher("/studentLogin.jsp")
				.forward(req, resp);

			} else {

				req.setAttribute("message", result);

				req.getRequestDispatcher("/forgotPassword.jsp")
				.forward(req, resp);
			}

		} catch (Exception e) {

			req.setAttribute("message", "Forgot password failed");

			req.getRequestDispatcher("/forgotPassword.jsp")
			.forward(req, resp);
		}
	}
}