package in.sp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.sp.dbconnection.DBConnection;
import in.sp.model.Student;

public class StudentRepository {

	private Connection getConnection() {
		return DBConnection.getInstance().getConnection();
	}

	public boolean savePendingStudent(Student student) throws Exception {

		String query = "INSERT INTO students(name,email,mobile,gender,occupation,aadhaar_id,password,status) "
				+ "VALUES(?,?,?,?,?,?,?,'PENDING')";

		PreparedStatement ps = getConnection().prepareStatement(query);

		ps.setString(1, student.getName());
		ps.setString(2, student.getEmail());
		ps.setString(3, student.getMobile());
		ps.setString(4, student.getGender());
		ps.setString(5, student.getOccupation());
		ps.setString(6, student.getAadhaarId());
		ps.setString(7, student.getPassword());

		return ps.executeUpdate() > 0;
	}

	public boolean emailExists(String email) throws Exception {

		PreparedStatement ps = getConnection().prepareStatement(
						"SELECT student_id FROM students WHERE email=?"
				);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}

	public boolean aadhaarExists(String aadhaarId) throws Exception {

		PreparedStatement ps = getConnection().prepareStatement(
						"SELECT student_id FROM students WHERE aadhaar_id=?"
				);

		ps.setString(1, aadhaarId);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}

	public Student findByEmail(String email) throws Exception {
		PreparedStatement ps = getConnection().prepareStatement(
						"SELECT * FROM students WHERE email=?"
				);

		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return mapStudent(rs);
		}

		return null;
	}
	public List<Student> pendingStudents() throws Exception {

		PreparedStatement ps = getConnection().prepareStatement(
						"SELECT * FROM students WHERE status='PENDING'"
				);

		ResultSet rs = ps.executeQuery();
		List<Student> list = new ArrayList<>();
		while (rs.next()) {
			list.add(mapStudent(rs));
		}
	return list;
	}

	public List<Student> activeMembers() throws Exception {

		PreparedStatement ps = getConnection().prepareStatement(
						"SELECT * FROM students WHERE status='APPROVED' AND membership_end >= CURDATE()"
				);
		ResultSet rs = ps.executeQuery();
		List<Student> list = new ArrayList<>();

		while (rs.next()) {
			list.add(mapStudent(rs));
		}
		return list;
	}
	public List<Student> searchStudents(String keyword) throws Exception {

		String query = "SELECT * FROM students "
				+ "WHERE name LIKE ? OR email LIKE ? OR mobile LIKE ?";

		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setString(1, "%" + keyword + "%");
		ps.setString(2, "%" + keyword + "%");
		ps.setString(3, "%" + keyword + "%");
		ResultSet rs = ps.executeQuery();

		List<Student> list = new ArrayList<>();

		while (rs.next()) {
			list.add(mapStudent(rs));
		}
		return list;
	}

	public int totalActiveMemberships() throws Exception {
		PreparedStatement ps = getConnection().prepareStatement(
						"SELECT COUNT(*) FROM students "
						+ "WHERE status='APPROVED' "
						+ "AND membership_end >= CURDATE()"
				);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	public boolean approveStudent(int studentId) throws Exception {
		PreparedStatement ps = getConnection().prepareStatement(
						"UPDATE students SET status='APPROVED', rejection_reason=NULL WHERE student_id=?"
				);

		ps.setInt(1, studentId);
		return ps.executeUpdate() > 0;
	}

	public boolean rejectStudent(int studentId, String reason) throws Exception {
		PreparedStatement ps = getConnection().prepareStatement(
						"UPDATE students SET status='REJECTED', rejection_reason=? WHERE student_id=?"
				);
		ps.setString(1, reason);
		ps.setInt(2, studentId);
		return ps.executeUpdate() > 0;
	}
	public boolean assignSeat(String email, int seatId) throws Exception {

		String query = "UPDATE students SET seat_id=?, "
				+ "membership_start=CURDATE(), "
				+ "membership_end=DATE_ADD(CURDATE(), INTERVAL 30 DAY), "
				+ "seat_reserved_until=NULL "
				+ "WHERE email=?";

		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setInt(1, seatId);
		ps.setString(2, email);
		return ps.executeUpdate() > 0;
	}

	public boolean renewMembership(String email) throws Exception {

		String query = "UPDATE students SET "
				+ "membership_start=CURDATE(), "
				+ "membership_end=DATE_ADD(CURDATE(), INTERVAL 30 DAY), "
				+ "seat_reserved_until=NULL "
				+ "WHERE email=?";

		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setString(1, email);
		return ps.executeUpdate() > 0;
	}

	public boolean updatePassword(String email, String password) throws Exception {

		PreparedStatement ps = getConnection().prepareStatement(
						"UPDATE students SET password=? WHERE email=?"
				);
		ps.setString(1, password);
		ps.setString(2, email);
		return ps.executeUpdate() > 0;
	}
	public boolean updateProfile(Student student) throws Exception {

		String query = "UPDATE students SET name=?, mobile=?, gender=?, occupation=?, aadhaar_id=? "
				+ "WHERE email=?";

		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setString(1, student.getName());
		ps.setString(2, student.getMobile());
		ps.setString(3, student.getGender());
		ps.setString(4, student.getOccupation());
		ps.setString(5, student.getAadhaarId());
		ps.setString(6, student.getEmail());
		return ps.executeUpdate() > 0;
	}

	public void saveLoginHistory(String email) throws Exception {
		PreparedStatement ps = getConnection().prepareStatement(
						"INSERT INTO login_history(email) VALUES(?)"
				);

		ps.setString(1, email);
		ps.executeUpdate();
	}

	private Student mapStudent(ResultSet rs) throws Exception {

		Student student = new Student();
		student.setStudentId(rs.getInt("student_id"));
		student.setName(rs.getString("name"));
		student.setEmail(rs.getString("email"));
		student.setMobile(rs.getString("mobile"));
		student.setGender(rs.getString("gender"));
		student.setOccupation(rs.getString("occupation"));
		student.setAadhaarId(rs.getString("aadhaar_id"));
		student.setPassword(rs.getString("password"));
		student.setStatus(rs.getString("status"));
		student.setMembershipStart(rs.getDate("membership_start"));
		student.setMembershipEnd(rs.getDate("membership_end"));
		student.setSeatId(rs.getInt("seat_id"));
		student.setSeatReservedUntil(rs.getDate("seat_reserved_until"));

		try {
			student.setRejectionReason(rs.getString("rejection_reason"));
		} catch(Exception e) {
			student.setRejectionReason(null);
		}
		return student;
	}
	
	public boolean requestSeatChange(
			String email,
			int requestedSeatId) throws Exception {

		Student student = findByEmail(email);

		String query = "INSERT INTO seat_change_requests"
				+ "(student_id,current_seat_id,requested_seat_id,status) "
				+ "VALUES(?,?,?,'PENDING')";

		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setInt(1, student.getStudentId());
		ps.setInt(2, student.getSeatId());
		ps.setInt(3, requestedSeatId);
		return ps.executeUpdate() > 0;
	}
	
	public boolean hasPendingSeatChangeRequest(String email) throws Exception {

		String query = "SELECT scr.request_id FROM seat_change_requests scr "
				+ "INNER JOIN students s ON scr.student_id = s.student_id "
				+ "WHERE s.email=? AND scr.status='PENDING'";

		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();

		return rs.next();
	}

	public List<in.sp.model.SeatChangeRequest> pendingSeatChangeRequests()
			throws Exception {

		String query = "SELECT scr.*, s.name, s.email "
				+ "FROM seat_change_requests scr "
				+ "INNER JOIN students s ON scr.student_id = s.student_id "
				+ "WHERE scr.status='PENDING' "
				+ "ORDER BY scr.request_id DESC";

		PreparedStatement ps = getConnection().prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		List<in.sp.model.SeatChangeRequest> list = new ArrayList<>();

		while(rs.next()) {

			in.sp.model.SeatChangeRequest request =	new in.sp.model.SeatChangeRequest();
			request.setRequestId(rs.getInt("request_id"));
			request.setStudentId(rs.getInt("student_id"));
			request.setStudentName(rs.getString("name"));
			request.setStudentEmail(rs.getString("email"));
			request.setCurrentSeatId(rs.getInt("current_seat_id"));
			request.setRequestedSeatId(rs.getInt("requested_seat_id"));
			request.setStatus(rs.getString("status"));

			list.add(request);
		}
		return list;
	}
	public boolean approveSeatChangeRequest(int requestId) throws Exception {

		String selectQuery = "SELECT * FROM seat_change_requests WHERE request_id=?";

		PreparedStatement ps = getConnection().prepareStatement(selectQuery);
		ps.setInt(1, requestId);
		ResultSet rs = ps.executeQuery();

		if(rs.next()) {

			int studentId = rs.getInt("student_id");
			int requestedSeatId = rs.getInt("requested_seat_id");
			String updateStudent = "UPDATE students SET seat_id=? WHERE student_id=?";
			PreparedStatement ps2 = getConnection().prepareStatement(updateStudent);
			ps2.setInt(1, requestedSeatId);
			ps2.setInt(2, studentId);
			ps2.executeUpdate();

			String updateRequest = "UPDATE seat_change_requests SET status='APPROVED' "
					+ "WHERE request_id=?";

			PreparedStatement ps3 = getConnection().prepareStatement(updateRequest);
			ps3.setInt(1, requestId);
			return ps3.executeUpdate() > 0;
		}
		return false;
	}

	public boolean rejectSeatChangeRequest(int requestId) throws Exception {

		PreparedStatement ps = getConnection().prepareStatement(
						"UPDATE seat_change_requests SET status='REJECTED' WHERE request_id=?"
				);
		ps.setInt(1, requestId);
		return ps.executeUpdate() > 0;
	}
	public boolean updateRejectedStudent(Student student) throws Exception {

		String query =
				"UPDATE students SET name=?, mobile=?, gender=?, "
				+ "occupation=?, aadhaar_id=?, password=?, "
				+ "status='PENDING', rejection_reason=NULL "
				+ "WHERE email=? AND status='REJECTED'";

		PreparedStatement ps =
				getConnection().prepareStatement(query);

		ps.setString(1, student.getName());
		ps.setString(2, student.getMobile());
		ps.setString(3, student.getGender());
		ps.setString(4, student.getOccupation());
		ps.setString(5, student.getAadhaarId());
		ps.setString(6, student.getPassword());
		ps.setString(7, student.getEmail());

		return ps.executeUpdate() > 0;
	}
}