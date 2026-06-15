package in.sp.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.sp.service.AdminService;

@WebServlet("/adminLogin")
public class AdminLogin extends HttpServlet {

	private AdminService adminService = new AdminService();

	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			boolean valid = adminService.login(req.getParameter("email"), // read email entered by admin
					req.getParameter("password") // read pass
					);

			if (valid) {
				HttpSession session = req.getSession(); // create session
				session.setAttribute("admin_email",req.getParameter("email")); // store admin email in session
				resp.sendRedirect("adminDashboard"); // open admin dashboard
			} else {
				req.setAttribute("message","Invalid admin email or password");
				req.getRequestDispatcher("/adminLogin.jsp")
				.forward(req, resp);
			}

		} catch (Exception e) {

			req.setAttribute("message","Admin login error");

			req.getRequestDispatcher("/adminLogin.jsp")
			.forward(req, resp);
		}
	}
}