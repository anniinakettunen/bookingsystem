package backend.bookingsystem.web;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import backend.bookingsystem.model.MeetingRoom;
import backend.bookingsystem.repository.MeetingRoomRepository;

@RestController
@RequestMapping("/api/rooms")
public class MeetingRoomController {

    private final MeetingRoomRepository repository;

    public MeetingRoomController(MeetingRoomRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public MeetingRoom create(@RequestBody MeetingRoom room) {
        return repository.save(room);
    }

    @GetMapping
    public List<MeetingRoom> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public MeetingRoom get(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }
}
