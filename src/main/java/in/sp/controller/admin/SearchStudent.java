package in.sp.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.sp.model.Student;
import in.sp.repository.StudentRepository;

@WebServlet("/searchStudent")
public class SearchStudent extends HttpServlet {

	private StudentRepository studentRepository =new StudentRepository();

	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			String keyword = req.getParameter("keyword");

			List<Student> students = studentRepository.searchStudents(keyword);

			req.setAttribute("students", students);

			req.getRequestDispatcher("/searchStudent.jsp").forward(req, resp);

		} catch (Exception e) {
			req.setAttribute("message", "Search failed");
			req.getRequestDispatcher("/searchStudent.jsp")
			.forward(req, resp);
		}
	}
}