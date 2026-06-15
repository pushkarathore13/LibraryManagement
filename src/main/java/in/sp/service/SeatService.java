package in.sp.service;

import java.util.List;

import in.sp.model.Seat;
import in.sp.repository.SeatRepository;

public class SeatService {

    private SeatRepository seatRepository = new SeatRepository();

    public int availableSeatCount() throws Exception {

        return seatRepository.availableSeatCount();
    }

    public List<Seat> availableSeats() throws Exception {

        return seatRepository.availableSeats();
    }
}