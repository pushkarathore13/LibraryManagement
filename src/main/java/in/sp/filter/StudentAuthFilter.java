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
		"/studentDashboard",
		"/studentDashboard.jsp",
		"/changePassword.jsp",
		"/editProfile.jsp"
})
public class StudentAuthFilter implements Filter {

	@Override
	public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession(false);

		if (session != null && session.getAttribute("student_email") != null) {

			chain.doFilter(request, response);

		} else {
			resp.sendRedirect("studentLogin.jsp");
		}
	}
}