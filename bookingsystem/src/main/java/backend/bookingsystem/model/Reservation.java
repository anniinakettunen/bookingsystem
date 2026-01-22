package backend.bookingsystem.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @NotNull(message = "Meeting room is required.")
    private MeetingRoom meetingRoom;

    @Column(nullable = false)
    @NotNull(message = "Start time is required.")
    private LocalDateTime startTime;

    @Column(nullable = false)
    @NotNull(message = "End time is required.")
    private LocalDateTime endTime;

    @Size(max = 500, message = "Additional info can be at most 500 characters.")
    private String additionalInfo;

    public Reservation() {}

    public Reservation(MeetingRoom meetingRoom,
                       LocalDateTime startTime,
                       LocalDateTime endTime,
                       String additionalInfo) {
        this.meetingRoom = meetingRoom;
        this.startTime = startTime;
        this.endTime = endTime;
        this.additionalInfo = additionalInfo;
    }

    public Long getId() {
        return id;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", meetingRoom=" + (meetingRoom != null ? meetingRoom.getId() : null) +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
