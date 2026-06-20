package in.sp.model;

import java.sql.Date;
import java.sql.Timestamp;

public class MembershipHistory {

	private int historyId;
	private int studentId;
	private String studentName;
	private String studentEmail;
	private int seatId;
	private String shiftName;
	private Date membershipStart;
	private Date membershipEnd;
	private String actionType;
	private String reason;
	private Timestamp createdAt;

	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
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

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}