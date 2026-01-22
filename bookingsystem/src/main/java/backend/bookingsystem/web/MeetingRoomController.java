package backend.bookingsystem.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<MeetingRoom> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
