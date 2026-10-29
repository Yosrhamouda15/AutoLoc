package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;

import java.util.List;

@Tag(name = "Employes", description = "Gestion des employés")
@RestController
@RequestMapping("employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmployeService service;

    @Operation(summary = "Lister")
    @GetMapping
    public List<Employe> findAll() { return service.findAll(); }

    @Operation(summary = "Consulter")
    @GetMapping("{id}")
    public Employe findById(@PathVariable Long id) { return service.findById(id); }

    @Operation(summary = "Créer")
    @PostMapping
    public ResponseEntity<Employe> create(@RequestBody Employe e) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(e));
    }

    @Operation(summary = "Modifier")
    @PutMapping("{id}")
    public Employe update(@PathVariable Long id, @RequestBody Employe e) { return service.update(id, e); }

    @Operation(summary = "Supprimer")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}