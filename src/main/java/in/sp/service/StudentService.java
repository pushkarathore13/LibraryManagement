package in.sp.service;

import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;

import in.sp.model.Student;
import in.sp.repository.StudentRepository;
import in.sp.util.RegexPatterns;

public class StudentService {

	private StudentRepository studentRepository =
			new StudentRepository();

	public String register(Student student) throws Exception {

		if (isEmpty(student.getName())) {
			return "Name cannot be empty";
		}

		if (!Pattern.matches(RegexPatterns.NAME_REGEX, student.getName())) {
			return "Name should contain alphabets only";
		}

		if (isEmpty(student.getEmail())) {
			return "Email cannot be empty";
		}

		if (!Pattern.matches(RegexPatterns.EMAIL_REGEX, student.getEmail())) {
			return "Invalid email format";
		}

		if (isEmpty(student.getMobile())) {
			return "Mobile number cannot be empty";
		}

		if (!Pattern.matches(RegexPatterns.MOBILE_REGEX, student.getMobile())) {
			return "Mobile number must contain 10 digits only";
		}

		if (isEmpty(student.getGender())) {
			return "Gender cannot be empty";
		}

		if (isEmpty(student.getOccupation())) {
			return "Occupation cannot be empty";
		}

		if (!Pattern.matches(RegexPatterns.NAME_REGEX, student.getOccupation())) {
			return "Occupation should contain alphabets only";
		}

		if (isEmpty(student.getAadhaarId())) {
			return "Aadhaar ID cannot be empty";
		}

		if (!Pattern.matches(RegexPatterns.AADHAAR_REGEX, student.getAadhaarId())) {
			return "Aadhaar ID must contain 12 digits only";
		}

		if (isEmpty(student.getPassword())) {
			return "Password cannot be empty";
		}

		if (!Pattern.matches(RegexPatterns.PASSWORD_REGEX, student.getPassword())) {
			return "Weak password! Use uppercase, lowercase, number, special character and minimum 8 characters";
		}

		Student existingStudent =
				studentRepository.findByEmail(student.getEmail());

		String hashedPassword =
				BCrypt.hashpw(
						student.getPassword(),
						BCrypt.gensalt()
				);

		student.setPassword(hashedPassword);

		if (existingStudent != null) {

			if ("REJECTED".equalsIgnoreCase(existingStudent.getStatus())) {

				boolean updated =
						studentRepository.updateRejectedStudent(student);

				if (updated) {
					return "success";
				} else {
					return "Registration update failed";
				}
			}

			if ("ENDED".equalsIgnoreCase(existingStudent.getStatus())) {
				return "Your membership was ended by admin. Please contact admin for reactivation.";
			}

			return "Email already exists";
		}

		if (studentRepository.aadhaarExists(student.getAadhaarId())) {
			return "Aadhaar ID already exists";
		}

		boolean saved =
				studentRepository.savePendingStudent(student);

		if (saved) {
			return "success";
		} else {
			return "Registration failed";
		}
	}

	public String login(
			String email,
			String password) throws Exception {

		if (isEmpty(email)) {
			return "Email cannot be empty";
		}

		if (!Pattern.matches(RegexPatterns.EMAIL_REGEX, email)) {
			return "Invalid email format";
		}

		if (isEmpty(password)) {
			return "Password cannot be empty";
		}

		Student student =
				studentRepository.findByEmail(email);

		if (student == null) {
			return "Email not found";
		}

		if ("PENDING".equalsIgnoreCase(student.getStatus())) {
			return "Your registration is pending admin approval";
		}

		if ("REJECTED".equalsIgnoreCase(student.getStatus())) {
			return "Your registration request was rejected";
		}

		if ("ENDED".equalsIgnoreCase(student.getStatus())) {

			String reason =
					student.getMembershipEndReason();

			if (reason == null || reason.trim().isEmpty()) {
				reason = "No reason provided";
			}

			return "Your membership has been ended by admin. Reason: "
					+ reason;
		}

		if (!BCrypt.checkpw(password, student.getPassword())) {
			return "Wrong password";
		}

		studentRepository.saveLoginHistory(email);

		return "success";
	}

	public String forgotPassword(
			String email,
			String newPassword) throws Exception {

		if (isEmpty(email)) {
			return "Email cannot be empty";
		}

		if (!Pattern.matches(RegexPatterns.EMAIL_REGEX, email)) {
			return "Invalid email format";
		}

		if (isEmpty(newPassword)) {
			return "New password cannot be empty";
		}

		if (!Pattern.matches(RegexPatterns.PASSWORD_REGEX, newPassword)) {
			return "Weak password! Use uppercase, lowercase, number, special character and minimum 8 characters";
		}

		Student student =
				studentRepository.findByEmail(email);

		if (student == null) {
			return "Email not found";
		}

		String hashedPassword =
				BCrypt.hashpw(
						newPassword,
						BCrypt.gensalt()
				);

		boolean updated =
				studentRepository.updatePassword(
						email,
						hashedPassword
				);

		if (updated) {
			return "success";
		} else {
			return "Password reset failed";
		}
	}

	public String changePassword(
			String email,
			String oldPassword,
			String newPassword) throws Exception {

		if (isEmpty(oldPassword)) {
			return "Old password cannot be empty";
		}

		if (isEmpty(newPassword)) {
			return "New password cannot be empty";
		}

		if (!Pattern.matches(RegexPatterns.PASSWORD_REGEX, newPassword)) {
			return "Weak password! Use uppercase, lowercase, number, special character and minimum 8 characters";
		}

		Student student =
				studentRepository.findByEmail(email);

		if (student == null) {
			return "User not found";
		}

		if (!BCrypt.checkpw(oldPassword, student.getPassword())) {
			return "Old password is incorrect";
		}

		String hashedPassword =
				BCrypt.hashpw(
						newPassword,
						BCrypt.gensalt()
				);

		boolean updated =
				studentRepository.updatePassword(
						email,
						hashedPassword
				);

		if (updated) {
			return "success";
		} else {
			return "Password change failed";
		}
	}

	public String updateProfile(Student student) throws Exception {

		if (isEmpty(student.getName())) {
			return "Name cannot be empty";
		}

		if (!Pattern.matches(RegexPatterns.NAME_REGEX, student.getName())) {
			return "Name should contain alphabets only";
		}

		if (isEmpty(student.getMobile())) {
			return "Mobile cannot be empty";
		}

		if (!Pattern.matches(RegexPatterns.MOBILE_REGEX, student.getMobile())) {
			return "Mobile number must contain 10 digits only";
		}

		if (isEmpty(student.getGender())) {
			return "Gender cannot be empty";
		}

		if (isEmpty(student.getOccupation())) {
			return "Occupation cannot be empty";
		}

		if (!Pattern.matches(RegexPatterns.NAME_REGEX, student.getOccupation())) {
			return "Occupation should contain alphabets only";
		}

		if (isEmpty(student.getAadhaarId())) {
			return "Aadhaar cannot be empty";
		}

		if (!Pattern.matches(RegexPatterns.AADHAAR_REGEX, student.getAadhaarId())) {
			return "Aadhaar ID must contain 12 digits only";
		}

		boolean updated =
				studentRepository.updateProfile(student);

		if (updated) {
			return "success";
		} else {
			return "Profile update failed";
		}
	}

	private boolean isEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}
}