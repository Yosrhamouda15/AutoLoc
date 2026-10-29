package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.repository.IReservationRepository;

@Service
public class ReservationServiceImpl extends GenericServiceImpl<Reservation, Long> implements IReservationService {
    public ReservationServiceImpl(IReservationRepository repository) { super(repository); }
    @Override protected String getEntityName() { return "Reservation"; }
    @Override protected Long getId(Reservation r) { return r.getIdReservation(); }
    @Override protected void setFields(Reservation s, Reservation t) {
        t.setDateDebut(s.getDateDebut()); t.setDateFin(s.getDateFin());
        t.setStatut(s.getStatut()); t.setClient(s.getClient());
        t.setVehicule(s.getVehicule()); t.setContrat(s.getContrat());
    }
    @Override public boolean check(Reservation r, boolean isNew) {
        return r.getDateDebut() != null && r.getDateFin() != null
                && !r.getDateFin().isBefore(r.getDateDebut())
                && r.getStatut() != null && r.getClient() != null && r.getVehicule() != null;
    }
}