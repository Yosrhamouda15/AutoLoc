package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.service.IMaintenanceService;

import java.util.List;

@Tag(name = "Maintenances", description = "Gestion des maintenances")
@RestController
@RequestMapping("maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenanceService service;

    @Operation(summary = "Lister")
    @GetMapping
    public List<Maintenance> findAll() { return service.findAll(); }

    @Operation(summary = "Consulter")
    @GetMapping("{id}")
    public Maintenance findById(@PathVariable Long id) { return service.findById(id); }

    @Operation(summary = "Créer")
    @PostMapping
    public ResponseEntity<Maintenance> create(@RequestBody Maintenance m) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(m));
    }

    @Operation(summary = "Modifier")
    @PutMapping("{id}")
    public Maintenance update(@PathVariable Long id, @RequestBody Maintenance m) { return service.update(id, m); }

    @Operation(summary = "Supprimer")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}