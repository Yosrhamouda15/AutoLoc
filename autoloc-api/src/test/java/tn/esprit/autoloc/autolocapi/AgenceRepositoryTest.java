package tn.esprit.autoloc.autolocapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class AgenceRepositoryTest {

    // étape 2 : attribut renommé
    @Autowired
    private AgenceRepositoryMock basicAgenceRepository;

    // étape 3 : le dépôt Spring Data JPA, DANS la classe
    @Autowired
    private IAgenceRepository fullAgenceRepository;

    // étapes 4, 5, 6 : privée, sans @Test, dépôt en paramètre
    private void addAgence(CrudRepository<Agence, Long> repository) {
        // 5 chiffres max, jamais négatif : "210 TUN 1234-" (13 car.) + 5 = 18 <= 20
        String suffixe = String.valueOf(System.currentTimeMillis() % 100000);

        Agence agence = new Agence();
        agence.setNom("AutoLoc Tunis Centre");
        agence.setVille("Tunis");
        agence.setAdresse("12 Avenue Habib Bourguiba");
        agence.setTelephone("71000000");

        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("210 TUN 1234-" + suffixe);
        v1.setMarque("Renault");
        v1.setModele("Clio");
        v1.setCategorie(CategorieVehicule.CITADINE);
        v1.setTarifJournalier(new BigDecimal("80.00"));
        v1.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("215 TUN 5678-" + suffixe);
        v2.setMarque("Peugeot");
        v2.setModele("308");
        v2.setCategorie(CategorieVehicule.BERLINE);
        v2.setTarifJournalier(new BigDecimal("120.00"));
        v2.setStatut(StatutVehicule.DISPONIBLE);

        agence.addVehicule(v1);
        agence.addVehicule(v2);

        repository.save(agence);
    }

    // étape 7
    @Test
    void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    @Test
    void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }

    // étape 10 : même principe pour le chargement
    private void loadAgence(CrudRepository<Agence, Long> repository, String typeDepot) {
        StringBuilder sb = new StringBuilder();
        sb.append("Dépôt utilisé : ").append(typeDepot).append("\n");
        for (Agence a : repository.findAll()) {
            sb.append("Agence ").append(a.getIdAgence())
                    .append(" : ").append(a.getNom()).append("\n");
            sb.append("  Nombre de vehicules : ")
                    .append(a.getVehicules().size()).append("\n");
            for (Vehicule v : a.getVehicules()) {
                sb.append("  - ").append(v.getIdVehicule())
                        .append(" / ").append(v.getImmatriculation()).append("\n");
            }
        }
        fail(sb.toString());
    }

    @Test
    void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "basic (AgenceRepositoryMock)");
    }

    @Test
    void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "full (IAgenceRepository / JpaRepository)");
    }

    // étapes 11 à 14 : agences triées par id décroissant
    @Test
    void loadSortedAgences() {
        List<Agence> agences = fullAgenceRepository.findAll(Sort.by(Sort.Direction.DESC, "idAgence"));

        StringBuilder sb = new StringBuilder();
        sb.append("Agences triées par id décroissant\n");
        for (Agence a : agences) {
            sb.append("Agence ").append(a.getIdAgence())
                    .append(" : ").append(a.getNom())
                    .append(" (").append(a.getVille()).append(")\n");
        }
        fail(sb.toString());
    }

    // étapes 15 à 18 : tri décroissant + pagination par lots de 2
    // (le tri est volontairement dupliqué par rapport à loadSortedAgences)
    // étapes 15 à 18 : tri décroissant + pagination par lots de 2, toutes les pages
    // (le tri est volontairement dupliqué par rapport à loadSortedAgences)
    @Test
    void loadPagedAgences() {
        Sort sort = Sort.by(Sort.Direction.DESC, "idAgence");
        StringBuilder sb = new StringBuilder();

        Page<Agence> premierePage = fullAgenceRepository.findAll(PageRequest.of(0, 2, sort));
        sb.append("Total pages : ").append(premierePage.getTotalPages()).append("\n");

        for (int i = 0; i < premierePage.getTotalPages(); i++) {
            Page<Agence> page = fullAgenceRepository.findAll(PageRequest.of(i, 2, sort));
            sb.append("--- Page en cours : ").append(page.getNumber()).append("\n");
            for (Agence a : page.getContent()) {
                sb.append(a.getIdAgence()).append(" | ").append(a.getNom()).append("\n");
            }
        }
        fail(sb.toString());
    }
}

interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}