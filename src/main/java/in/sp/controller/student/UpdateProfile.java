package in.sp.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.sp.model.Student;
import in.sp.service.StudentService;

@WebServlet("/updateProfile")
public class UpdateProfile extends HttpServlet {

	private StudentService studentService = new StudentService();

	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			Student student = new Student();

			student.setEmail((String) req.getSession().getAttribute("student_email"));

			student.setName(req.getParameter("name"));
			student.setMobile(req.getParameter("mobile"));
			student.setGender(req.getParameter("gender"));
			student.setOccupation(req.getParameter("occupation"));
			student.setAadhaarId(req.getParameter("aadhaar_id"));

			String result = studentService.updateProfile(student);

			if ("success".equals(result)) {req.setAttribute("message","Profile updated successfully");
			} else {
				req.setAttribute("message", result);
			}

			req.getRequestDispatcher("/editProfile.jsp").forward(req, resp);

		} catch (Exception e) {

			req.setAttribute("message","Update profile failed");

			req.getRequestDispatcher("/editProfile.jsp").forward(req, resp);
		}
	}
}