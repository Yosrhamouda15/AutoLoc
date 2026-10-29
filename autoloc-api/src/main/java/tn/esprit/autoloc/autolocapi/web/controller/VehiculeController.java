package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.service.IVehiculeService;

import java.util.List;

@Tag(name = "Vehicules", description = "Gestion des véhicules")
@RestController
@RequestMapping("vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService service;

    @Operation(summary = "Lister")
    @GetMapping
    public List<Vehicule> findAll() { return service.findAll(); }

    @Operation(summary = "Consulter")
    @GetMapping("{id}")
    public Vehicule findById(@PathVariable Long id) { return service.findById(id); }

    @Operation(summary = "Créer")
    @PostMapping
    public ResponseEntity<Vehicule> create(@RequestBody Vehicule v) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(v));
    }

    @Operation(summary = "Modifier")
    @PutMapping("{id}")
    public Vehicule update(@PathVariable Long id, @RequestBody Vehicule v) { return service.update(id, v); }

    @Operation(summary = "Supprimer")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}