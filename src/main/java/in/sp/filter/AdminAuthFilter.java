package in.sp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {
		"/adminDashboard.jsp",
		"/pendingStudents",
		"/searchStudent.jsp",
		"/approveStudent",
		"/rejectStudent"
})
public class AdminAuthFilter implements Filter {

	@Override
	public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain) // executes before protected page open
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession(false);

		if (session != null && session.getAttribute("admin_email") != null) {

			chain.doFilter(request, response); // allow access

		} else {
			resp.sendRedirect("adminLogin.jsp");
		}
	}
}