package in.sp.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import in.sp.repository.StudentRepository;

@WebServlet("/membershipHistory")
public class MembershipHistory extends HttpServlet {

	private StudentRepository studentRepository =
			new StudentRepository();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			List<in.sp.model.MembershipHistory> historyList =
					studentRepository.membershipHistory();

			req.setAttribute("historyList", historyList);

			req.getRequestDispatcher("/membershipHistory.jsp")
			.forward(req, resp);

		} catch(Exception e) {
			req.setAttribute("message", "Unable to load membership history");
			req.getRequestDispatcher("/adminDashboard")
			.forward(req, resp);
		}
	}
}