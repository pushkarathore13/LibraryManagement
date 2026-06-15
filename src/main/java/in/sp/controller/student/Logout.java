package in.sp.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/logout")
public class Logout extends HttpServlet {

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		if(session != null) {
			session.invalidate();
		}

		resp.setHeader("Cache-Control","no-cache, no-store, must-revalidate");// prevents browser from caching pages

		resp.setHeader("Pragma","no-cache"); // cache control for old browser

		resp.setDateHeader(
				"Expires",
				0
		);

		resp.sendRedirect("index.jsp");
	}
}