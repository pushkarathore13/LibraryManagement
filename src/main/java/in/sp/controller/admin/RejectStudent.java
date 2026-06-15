package in.sp.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.service.AdminService;

@WebServlet("/rejectStudent")
public class RejectStudent extends HttpServlet {

	private AdminService adminService = new AdminService();

	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			int studentId = Integer.parseInt(req.getParameter("id"));

			String reason = req.getParameter("reason");

			if(reason == null || reason.trim().isEmpty()) {
				reason = "Request rejected by admin";
			}
			adminService.rejectStudent(studentId, reason);
			resp.sendRedirect("pendingStudents");
		} catch(Exception e) {
			req.setAttribute("message", "Reject failed");
			req.getRequestDispatcher("/pendingStudents")
			.forward(req, resp);
		}
	}
}