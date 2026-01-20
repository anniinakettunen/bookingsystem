package backend.bookingsystem.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.bookingsystem.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByMeetingRoomId(Long meetingRoomId);

    boolean isRoomOccupied(
            Long meetingRoomId,
            LocalDateTime startTime,
            LocalDateTime endTime
            
    );
}
