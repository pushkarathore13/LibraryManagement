package in.sp.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.sp.service.AdminService;

@WebServlet("/approveStudent")
public class ApproveStudent extends HttpServlet {

	private AdminService adminService =
			new AdminService();

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			int studentId = Integer.parseInt(req.getParameter("id")); 

			adminService.approveStudent(studentId);

			resp.sendRedirect("pendingStudents");

		} catch (Exception e) {

			req.setAttribute("message", "Approve failed");

			req.getRequestDispatcher("/pendingStudents")
			.forward(req, resp);
		}
	}
}