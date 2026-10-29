package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.repository.IClientRepository;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client, Long> implements IClientService {
    public ClientServiceImpl(IClientRepository repository) { super(repository); }
    @Override protected String getEntityName() { return "Client"; }
    @Override protected Long getId(Client c) { return c.getIdClient(); }
    @Override protected void setFields(Client s, Client t) {
        t.setNom(s.getNom()); t.setPrenom(s.getPrenom()); t.setEmail(s.getEmail());
        t.setTelephone(s.getTelephone()); t.setNumPermis(s.getNumPermis());
        t.setDateInscription(s.getDateInscription());
    }
    @Override public boolean check(Client c, boolean isNew) {
        return Utils.isNotEmpty(c.getNom()) && Utils.isNotEmpty(c.getPrenom())
                && Utils.isNotEmpty(c.getEmail()) && Utils.isNotEmpty(c.getTelephone())
                && Utils.isNotEmpty(c.getNumPermis());
    }
}