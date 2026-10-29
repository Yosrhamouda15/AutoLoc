package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.repository.IMaintenanceRepository;

@Service
public class MaintenanceServiceImpl extends GenericServiceImpl<Maintenance, Long> implements IMaintenanceService {
    public MaintenanceServiceImpl(IMaintenanceRepository repository) { super(repository); }
    @Override protected String getEntityName() { return "Maintenance"; }
    @Override protected Long getId(Maintenance m) { return m.getIdMaintenance(); }
    @Override protected void setFields(Maintenance s, Maintenance t) {
        t.setDateDebut(s.getDateDebut()); t.setDateFin(s.getDateFin());
        t.setDescription(s.getDescription()); t.setVehicule(s.getVehicule());
    }
    @Override public boolean check(Maintenance m, boolean isNew) {
        if (m.getDateDebut() == null || !Utils.isNotEmpty(m.getDescription())) return false;
        return m.getDateFin() == null || !m.getDateFin().isBefore(m.getDateDebut());
    }
}