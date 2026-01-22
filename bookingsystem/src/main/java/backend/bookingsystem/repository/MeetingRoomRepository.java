package backend.bookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.bookingsystem.model.MeetingRoom;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {
}
