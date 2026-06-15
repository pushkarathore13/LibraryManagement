package in.sp.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.sp.service.StudentService;

@WebServlet("/changePassword")
public class ChangePassword extends HttpServlet {

	private StudentService studentService = new StudentService();

	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			String email = (String) req.getSession()
					.getAttribute("student_email");

			String result = studentService.changePassword( email, req.getParameter("oldPassword"), req.getParameter("newPassword"));

			if ("success".equals(result)) {req.setAttribute("message","Password changed successfully");
			} else {
				req.setAttribute("message", result);
			}

			req.getRequestDispatcher("/changePassword.jsp")
			.forward(req, resp);

		} catch (Exception e) {

			req.setAttribute("message","Password change failed");

			req.getRequestDispatcher("/changePassword.jsp")
			.forward(req, resp);
		}
	}
}