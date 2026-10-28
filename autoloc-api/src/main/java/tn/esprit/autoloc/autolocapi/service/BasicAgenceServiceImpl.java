package tn.esprit.autoloc.autolocapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.exception.BusinessException;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;

import java.util.List;

@RequiredArgsConstructor
public class BasicAgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    @Override
    public boolean check(Agence agence, boolean isNew) {
        return Utils.isNotEmpty(agence.getNom())
                && Utils.isNotEmpty(agence.getVille())
                && Utils.isNotEmpty(agence.getAdresse())
                && Utils.isNotEmpty(agence.getTelephone());
    }

    @Override
    public Agence create(Agence agence) {
        if (agence.getIdAgence() != null) {
            throw new IllegalArgumentException("L'identifiant doit être vide à la création");
        }
        if (!check(agence, true)) {
            throw new BusinessException("Agence invalide");
        }
        return agenceRepository.save(agence);
    }

    @Override
    public Agence findById(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable : " + id));
    }

    @Override
    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence update(Long id, Agence agence) {
        Agence existing = findById(id);
        if (!check(agence, false)) {
            throw new BusinessException("Agence invalide");
        }
        existing.setNom(agence.getNom());
        existing.setVille(agence.getVille());
        existing.setAdresse(agence.getAdresse());
        existing.setTelephone(agence.getTelephone());
        return agenceRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!agenceRepository.existsById(id)) {
            throw new EntityNotFoundException("Agence introuvable : " + id);
        }
        agenceRepository.deleteById(id);
    }

    @Override
    public boolean hasVehicules(Long id) {
        Agence a = findById(id);
        return a.getVehicules() != null && !a.getVehicules().isEmpty();
    }
}