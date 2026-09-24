package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String immatriculation;
    private String marque;
    private String modele;
    private int annee;
    private double kilometrage;
    private double prixLocationJournalier;

    @Enumerated(EnumType.STRING)
    private StatutVehicule statut;

    @Enumerated(EnumType.STRING)
    private CategorieVehicule categorie;
}