package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.service.IReservationService;

import java.util.List;

@Tag(name = "Reservations", description = "Gestion des réservations")
@RestController
@RequestMapping("reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService service;

    @Operation(summary = "Lister")
    @GetMapping
    public List<Reservation> findAll() { return service.findAll(); }

    @Operation(summary = "Consulter")
    @GetMapping("{id}")
    public Reservation findById(@PathVariable Long id) { return service.findById(id); }

    @Operation(summary = "Créer")
    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody Reservation r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));
    }

    @Operation(summary = "Modifier")
    @PutMapping("{id}")
    public Reservation update(@PathVariable Long id, @RequestBody Reservation r) { return service.update(id, r); }

    @Operation(summary = "Supprimer")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}