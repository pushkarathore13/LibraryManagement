package in.sp.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter(urlPatterns = {
		"/studentDashboard",
		"/adminDashboard",
		"/pendingStudents",
		"/approveStudent",
		"/rejectStudent",
		"/searchStudent"
})
public class LoginFilter implements Filter {

	@Override
	public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletResponse resp = (HttpServletResponse) response;

		resp.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); // prevents browser caching

		resp.setHeader("Pragma","no-cache"); // additional support to old browser

		resp.setDateHeader("Expires",0); // tells browser page has expired

		HttpSession session = req.getSession(false);

		String uri = req.getRequestURI();

		if(uri.contains("studentDashboard")) {

			if(session != null &&
					session.getAttribute(
							"student_email"
					) != null) {

				chain.doFilter(
						request,
						response
				);

			} else {
				resp.sendRedirect(
						"login.jsp"
				);
			}

			return;
		}

		if(uri.contains("adminDashboard")
				|| uri.contains("pendingStudents")
				|| uri.contains("approveStudent")
				|| uri.contains("rejectStudent")
				|| uri.contains("searchStudent")) {

			if(session != null &&
					session.getAttribute(
							"admin_email"
					) != null) {

				chain.doFilter(
						request,
						response
				);

			} else {

				resp.sendRedirect(
						"login.jsp"
				);
			}

			return;
		}

		chain.doFilter(
				request,
				response
		);
	}
}