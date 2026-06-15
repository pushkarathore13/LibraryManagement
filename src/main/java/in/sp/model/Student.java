package in.sp.model;

import java.sql.Date;

public class Student {

	private int studentId;
	private String name;
	private String email;
	private String mobile;
	private String gender;
	private String occupation;
	private String aadhaarId;
	private String password;
	private String status;

	private String rejectionReason;

	private Date membershipStart;
	private Date membershipEnd;

	private int seatId;
	private Date seatReservedUntil;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getAadhaarId() {
		return aadhaarId;
	}

	public void setAadhaarId(String aadhaarId) {
		this.aadhaarId = aadhaarId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public Date getMembershipStart() {
		return membershipStart;
	}

	public void setMembershipStart(Date membershipStart) {
		this.membershipStart = membershipStart;
	}

	public Date getMembershipEnd() {
		return membershipEnd;
	}

	public void setMembershipEnd(Date membershipEnd) {
		this.membershipEnd = membershipEnd;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public Date getSeatReservedUntil() {
		return seatReservedUntil;
	}

	public void setSeatReservedUntil(Date seatReservedUntil) {
		this.seatReservedUntil = seatReservedUntil;
	}
}