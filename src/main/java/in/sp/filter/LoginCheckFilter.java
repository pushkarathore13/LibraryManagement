package in.sp.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter({
	"/adminDashboard",
	"/activeMembers",
	"/allStudents",
	"/seatChangeRequests",
	"/membershipHistory",
	"/endMembershipPage",
	"/endingSoonMembers",
	"/expiredMembers",
	"/pendingStudents",
	"/studentDashboard",
	"/requestSeatChangePage",
	"/selectSeat",
	"/requestSeatChange",
	"/renewMembership"
})
public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req =
				(HttpServletRequest) request;

		HttpServletResponse resp =
				(HttpServletResponse) response;

		HttpSession session =
				req.getSession(false);

		String uri =
				req.getRequestURI();

		boolean adminPage =
				uri.contains("adminDashboard")
				|| uri.contains("activeMembers")
				|| uri.contains("allStudents")
				|| uri.contains("seatChangeRequests")
				|| uri.contains("membershipHistory")
				|| uri.contains("endMembershipPage")
				|| uri.contains("endingSoonMembers")
				|| uri.contains("expiredMembers")
				|| uri.contains("pendingStudents");

		boolean studentPage =
				uri.contains("studentDashboard")
				|| uri.contains("requestSeatChangePage")
				|| uri.contains("selectSeat")
				|| uri.contains("requestSeatChange")
				|| uri.contains("renewMembership");

		if(adminPage) {

			if(session == null ||
					session.getAttribute("admin_email") == null) {

				resp.sendRedirect("login.jsp");
				return;
			}
		}

		if(studentPage) {

			if(session == null ||
					session.getAttribute("student_email") == null) {

				resp.sendRedirect("login.jsp");
				return;
			}
		}

		chain.doFilter(request, response);
	}
}