package backend.bookingsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import backend.bookingsystem.model.MeetingRoom;
import backend.bookingsystem.model.Reservation;
import backend.bookingsystem.repository.ReservationRepository;
import jakarta.persistence.EntityManager;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final EntityManager entityManager;

    public ReservationService(ReservationRepository reservationRepository,
                              EntityManager entityManager) {
        this.reservationRepository = reservationRepository;
        this.entityManager = entityManager;
    }

    public Reservation create(Long roomId, LocalDateTime startTime, LocalDateTime endTime) {

        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        if (startTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Reservation cannot be in the past");
        }

        boolean overlap = reservationRepository
                .isRoomOccupied(
                        roomId, startTime, endTime
                );

        if (overlap) {
            throw new IllegalArgumentException("Meeting room already reserved for this time");
        }

        MeetingRoom room = entityManager.getReference(MeetingRoom.class, roomId);

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
