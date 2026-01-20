package backend.bookingsystem.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.bookingsystem.model.Reservation;
import backend.bookingsystem.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation create(
            @RequestParam Long roomId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end
    ) {
        return reservationService.create(roomId, start, end);
    }

    @DeleteMapping("/{id}")
    public void cancel(@PathVariable Long id) {
        reservationService.cancel(id);
    }

    @GetMapping("/room/{roomId}")
    public List<Reservation> list(@PathVariable Long roomId) {
        return reservationService.findByRoom(roomId);
    }
}
