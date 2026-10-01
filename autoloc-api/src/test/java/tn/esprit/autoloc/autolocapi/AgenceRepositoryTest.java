package tn.esprit.autoloc.autolocapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class AgenceRepositoryTest {

    @Autowired
    private AgenceRepositoryMock agenceRepository;

    @Test
    void addAgence() {
        Agence agence = new Agence();
        agence.setNom("AutoLoc Tunis Centre");
        agence.setVille("Tunis");
        agence.setAdresse("12 Avenue Habib Bourguiba");
        agence.setTelephone("71000000");

        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("210 TUN 1234");
        v1.setMarque("Renault");
        v1.setModele("Clio");
        v1.setCategorie(CategorieVehicule.CITADINE);
        v1.setTarifJournalier(new BigDecimal("80.00"));
        v1.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("215 TUN 5678");
        v2.setMarque("Peugeot");
        v2.setModele("308");
        v2.setCategorie(CategorieVehicule.BERLINE);
        v2.setTarifJournalier(new BigDecimal("120.00"));
        v2.setStatut(StatutVehicule.DISPONIBLE);

        agence.addVehicule(v1);
        agence.addVehicule(v2);

        agenceRepository.save(agence);
    }

    @Test
    void loadAgence() {
        StringBuilder sb = new StringBuilder();
        for (Agence a : agenceRepository.findAll()) {
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
}

interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}