package in.sp.service;

import org.mindrot.jbcrypt.BCrypt;

import in.sp.repository.AdminRepository;
import in.sp.repository.StudentRepository;

public class AdminService {

	private AdminRepository adminRepository =
			new AdminRepository();

	private StudentRepository studentRepository =
			new StudentRepository();

	public boolean login(String email, String password) throws Exception {

		String dbPassword =
				adminRepository.getAdminPasswordByEmail(email);

		if (dbPassword == null) {
			return false;
		}

		if (dbPassword.startsWith("$2a$")) {
			return BCrypt.checkpw(password, dbPassword);
		}

		return password.equals(dbPassword);
	}

	public boolean approveStudent(int studentId) throws Exception {
		return studentRepository.approveStudent(studentId);
	}

	public boolean rejectStudent(int studentId, String reason) throws Exception {
		return studentRepository.rejectStudent(studentId, reason);
	}
}