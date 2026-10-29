package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.service.IEquipementService;

import java.util.List;

@Tag(name = "Equipements", description = "Gestion des équipements")
@RestController
@RequestMapping("equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipementService service;

    @Operation(summary = "Lister")
    @GetMapping
    public List<Equipement> findAll() { return service.findAll(); }

    @Operation(summary = "Consulter")
    @GetMapping("{id}")
    public Equipement findById(@PathVariable Long id) { return service.findById(id); }

    @Operation(summary = "Créer")
    @PostMapping
    public ResponseEntity<Equipement> create(@RequestBody Equipement e) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(e));
    }

    @Operation(summary = "Modifier")
    @PutMapping("{id}")
    public Equipement update(@PathVariable Long id, @RequestBody Equipement e) { return service.update(id, e); }

    @Operation(summary = "Supprimer")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}