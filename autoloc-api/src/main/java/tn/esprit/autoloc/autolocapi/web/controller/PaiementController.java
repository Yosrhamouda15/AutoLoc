package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Paiement;
import tn.esprit.autoloc.autolocapi.service.IPaiementService;

import java.util.List;

@Tag(name = "Paiements", description = "Consultation des paiements (lecture seule)")
@RestController
@RequestMapping("paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService service;

    @Operation(summary = "Lister")
    @GetMapping
    public List<Paiement> findAll() { return service.findAll(); }

    @Operation(summary = "Consulter")
    @GetMapping("{id}")
    public Paiement findById(@PathVariable Long id) { return service.findById(id); }
}