package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.repository.IContratRepository;
import java.math.BigDecimal;

@Service
public class ContratServiceImpl extends GenericServiceImpl<Contrat, Long> implements IContratService {
    public ContratServiceImpl(IContratRepository repository) { super(repository); }
    @Override protected String getEntityName() { return "Contrat"; }
    @Override protected Long getId(Contrat c) { return c.getIdContrat(); }
    @Override protected void setFields(Contrat s, Contrat t) {
        t.setDateSignature(s.getDateSignature());
        t.setMontantTotal(s.getMontantTotal());
        t.setValide(s.isValide());
    }
    @Override public boolean check(Contrat c, boolean isNew) {
        return c.getDateSignature() != null && c.getMontantTotal() != null
                && c.getMontantTotal().compareTo(BigDecimal.ZERO) >= 0;
    }
}