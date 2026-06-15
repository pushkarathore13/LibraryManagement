package in.sp.model;

public class SeatChangeRequest {

	private int requestId;
	private int studentId;
	private String studentName;
	private String studentEmail;
	private int currentSeatId;
	private int requestedSeatId;
	private String status;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
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

	public int getCurrentSeatId() {
		return currentSeatId;
	}

	public void setCurrentSeatId(int currentSeatId) {
		this.currentSeatId = currentSeatId;
	}

	public int getRequestedSeatId() {
		return requestedSeatId;
	}

	public void setRequestedSeatId(int requestedSeatId) {
		this.requestedSeatId = requestedSeatId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}