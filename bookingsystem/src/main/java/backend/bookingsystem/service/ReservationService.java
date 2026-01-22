package backend.bookingsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import backend.bookingsystem.model.MeetingRoom;
import backend.bookingsystem.model.Reservation;
import backend.bookingsystem.repository.MeetingRoomRepository;
import backend.bookingsystem.repository.ReservationRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MeetingRoomRepository meetingRoomRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              MeetingRoomRepository meetingRoomRepository) {
        this.reservationRepository = reservationRepository;
        this.meetingRoomRepository = meetingRoomRepository;
    }

    public Reservation create(Long roomId, LocalDateTime startTime, LocalDateTime endTime) {

        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        if (startTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Reservation cannot be in the past");
        }

        boolean overlap = reservationRepository.isRoomOccupied(roomId, startTime, endTime);
        if (overlap) {
            throw new IllegalArgumentException("Meeting room already reserved for this time");
        }

        MeetingRoom room = meetingRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Meeting room not found"));

        Reservation reservation = new Reservation();
        reservation.setMeetingRoom(room);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);

        return reservationRepository.save(reservation);
    }

    public void cancel(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    public List<Reservation> findByRoom(Long roomId) {
        return reservationRepository.findByMeetingRoomId(roomId);
    }
}

