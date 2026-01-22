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

    public Reservation create(Long roomId, LocalDateTime startTime, LocalDateTime endTime, String nickname) {

    if (!startTime.isBefore(endTime)) {
        throw new IllegalArgumentException("Aloitusajan täytyy olla ennen lopetusaikaa.");
    }

    if (startTime.isBefore(LocalDateTime.now())) {
        throw new IllegalArgumentException("Varausta ei voi tehdä menneisyyteen.");
    }

    if (startTime.getMinute() != 0 || endTime.getMinute() != 0) {
        throw new IllegalArgumentException("Varaus on tehtävä tasatunnein (esim. 16:00–17:00).");
    }

    if (!startTime.plusHours(1).equals(endTime)) {
        throw new IllegalArgumentException("Varausajan on oltava tasan 1 tunti.");
    }

    boolean overlap = reservationRepository.isRoomOccupied(roomId, startTime, endTime);
    if (overlap) {
        throw new IllegalArgumentException("Huone on jo varattu kyseiselle ajalle.");
    }

    MeetingRoom room = meetingRoomRepository.findById(roomId)
            .orElseThrow(() -> new IllegalArgumentException("Huonetta ei löytynyt."));

    Reservation reservation = new Reservation();
    reservation.setMeetingRoom(room);
    reservation.setStartTime(startTime);
    reservation.setEndTime(endTime);

    reservation.setNickname(nickname);

    return reservationRepository.save(reservation);
}


    public void cancel(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    public List<Reservation> findByRoom(Long roomId) {
        return reservationRepository.findByMeetingRoomId(roomId);
    }
}

