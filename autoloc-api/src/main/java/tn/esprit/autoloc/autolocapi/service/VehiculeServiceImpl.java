package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.IVehiculeRepository;
import java.math.BigDecimal;

@Service
public class VehiculeServiceImpl extends GenericServiceImpl<Vehicule, Long> implements IVehiculeService {
    public VehiculeServiceImpl(IVehiculeRepository repository) { super(repository); }
    @Override protected String getEntityName() { return "Vehicule"; }
    @Override protected Long getId(Vehicule v) { return v.getIdVehicule(); }
    @Override protected void setFields(Vehicule s, Vehicule t) {
        t.setImmatriculation(s.getImmatriculation()); t.setMarque(s.getMarque());
        t.setModele(s.getModele()); t.setCategorie(s.getCategorie());
        t.setTarifJournalier(s.getTarifJournalier()); t.setStatut(s.getStatut());
        t.setAgence(s.getAgence());
    }
    @Override public boolean check(Vehicule v, boolean isNew) {
        return Utils.isNotEmpty(v.getImmatriculation()) && v.getImmatriculation().length() <= 20
                && Utils.isNotEmpty(v.getMarque()) && Utils.isNotEmpty(v.getModele())
                && v.getCategorie() != null && v.getStatut() != null
                && v.getTarifJournalier() != null && v.getTarifJournalier().compareTo(BigDecimal.ZERO) > 0;
    }
}