package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.repository.IEmployeRepository;

@Service
public class EmployeServiceImpl extends GenericServiceImpl<Employe, Long> implements IEmployeService {
    public EmployeServiceImpl(IEmployeRepository repository) { super(repository); }
    @Override protected String getEntityName() { return "Employe"; }
    @Override protected Long getId(Employe e) { return e.getIdEmploye(); }
    @Override protected void setFields(Employe s, Employe t) {
        t.setNom(s.getNom()); t.setPrenom(s.getPrenom());
        t.setRole(s.getRole()); t.setAgence(s.getAgence());
    }
    @Override public boolean check(Employe e, boolean isNew) {
        return Utils.isNotEmpty(e.getNom()) && Utils.isNotEmpty(e.getPrenom()) && e.getRole() != null;
    }
}