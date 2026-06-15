package in.sp.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.sp.dbconnection.DBConnection;
import in.sp.model.Seat;

public class SeatRepository {

	public int availableSeatCount() throws Exception {

		PreparedStatement ps =
				DBConnection.getInstance()
				.getConnection()
				.prepareStatement(
						"SELECT COUNT(*) FROM seats WHERE seat_id NOT IN "
						+ "(SELECT seat_id FROM students "
						+ "WHERE seat_id IS NOT NULL "
						+ "AND (membership_end >= CURDATE() "
						+ "OR seat_reserved_until >= CURDATE()))"
				);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getInt(1);
		}

		return 0;
	}

	public List<Seat> availableSeats() throws Exception {

		String query =
				"SELECT s.*, sh.shift_name "
				+ "FROM seats s "
				+ "LEFT JOIN shifts sh "
				+ "ON s.shift_id = sh.shift_id "
				+ "WHERE s.seat_id NOT IN "
				+ "(SELECT seat_id FROM students "
				+ "WHERE seat_id IS NOT NULL "
				+ "AND (membership_end >= CURDATE() "
				+ "OR seat_reserved_until >= CURDATE())) "
				+ "ORDER BY s.floor_no, s.seat_id";

		PreparedStatement ps =
				DBConnection.getInstance()
				.getConnection()
				.prepareStatement(query);

		ResultSet rs = ps.executeQuery();

		List<Seat> list = new ArrayList<>();

		while (rs.next()) {

			Seat seat = new Seat();

			seat.setSeatId(rs.getInt("seat_id"));
			seat.setFloorNo(rs.getInt("floor_no"));
			seat.setSeatNumber(rs.getString("seat_number"));
			seat.setStatus(rs.getString("status"));
			seat.setShiftId(rs.getInt("shift_id"));
			seat.setShiftName(rs.getString("shift_name"));

			list.add(seat);
		}

		return list;
	}

	public List<Seat> availableSeatsForNewUser() throws Exception {

		String query =
				"SELECT * FROM seats "
				+ "WHERE seat_id NOT IN "
				+ "(SELECT seat_id FROM students "
				+ "WHERE seat_id IS NOT NULL "
				+ "AND (membership_end >= CURDATE() "
				+ "OR seat_reserved_until >= CURDATE())) "
				+ "ORDER BY floor_no, seat_id";

		PreparedStatement ps =
				DBConnection.getInstance()
				.getConnection()
				.prepareStatement(query);

		ResultSet rs = ps.executeQuery();

		List<Seat> list = new ArrayList<>();

		while (rs.next()) {

			Seat seat = new Seat();

			seat.setSeatId(rs.getInt("seat_id"));
			seat.setFloorNo(rs.getInt("floor_no"));
			seat.setSeatNumber(rs.getString("seat_number"));
			seat.setStatus(rs.getString("status"));
			seat.setShiftId(rs.getInt("shift_id"));

			list.add(seat);
		}

		return list;
	}
	public List<Seat> allSeats() throws Exception {

		String query =
				"SELECT s.*, " +
				"CASE WHEN st.student_id IS NULL THEN 0 ELSE 1 END occupied " +
				"FROM seats s " +
				"LEFT JOIN students st ON s.seat_id = st.seat_id " +
				"AND (st.membership_end >= CURDATE() " +
				"OR st.seat_reserved_until >= CURDATE()) " +
				"ORDER BY s.floor_no, s.seat_id";

		PreparedStatement ps =
				DBConnection.getInstance()
				.getConnection()
				.prepareStatement(query);

		ResultSet rs = ps.executeQuery();

		List<Seat> list = new ArrayList<>();

		while(rs.next()) {

			Seat seat = new Seat();

			seat.setSeatId(rs.getInt("seat_id"));
			seat.setFloorNo(rs.getInt("floor_no"));
			seat.setSeatNumber(rs.getString("seat_number"));
			seat.setStatus(rs.getString("status"));
			seat.setShiftId(rs.getInt("shift_id"));

			seat.setOccupied(
					rs.getInt("occupied") == 1
			);

			list.add(seat);
		}

		return list;
	}
}