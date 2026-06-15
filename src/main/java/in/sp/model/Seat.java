package in.sp.model;

public class Seat {

	private int seatId;
	private int floorNo;
	private int shiftId;

	private String seatNumber;
	private String status;
	private String shiftName;

	// NEW FIELD
	private boolean occupied;

	public int getSeatId() { // use to read value
		return seatId;
	}

	public void setSeatId(int seatId) { // use to store value
		this.seatId = seatId;
	}

	public int getFloorNo() {
		return floorNo; 
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	// NEW GETTER SETTER

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
}