package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.service.IContratService;

import java.util.List;

@Tag(name = "Contrats", description = "Gestion des contrats")
@RestController
@RequestMapping("contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService service;

    @Operation(summary = "Lister")
    @GetMapping
    public List<Contrat> findAll() { return service.findAll(); }

    @Operation(summary = "Consulter")
    @GetMapping("{id}")
    public Contrat findById(@PathVariable Long id) { return service.findById(id); }

    @Operation(summary = "Créer")
    @PostMapping
    public ResponseEntity<Contrat> create(@RequestBody Contrat c) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(c));
    }

    @Operation(summary = "Modifier")
    @PutMapping("{id}")
    public Contrat update(@PathVariable Long id, @RequestBody Contrat c) { return service.update(id, c); }

    @Operation(summary = "Supprimer")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}