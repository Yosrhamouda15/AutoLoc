package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Agence;
import java.util.List;

public interface IAgenceService {
    boolean check(Agence agence, boolean isNew);
    Agence create(Agence agence);
    Agence findById(Long id);
    List<Agence> findAll();
    Agence update(Long id, Agence agence);
    void deleteById(Long id);
    boolean hasVehicules(Long id);
}