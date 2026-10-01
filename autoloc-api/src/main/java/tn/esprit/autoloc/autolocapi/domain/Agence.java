package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    private String nom;
    private String ville;
    private String adresse;
    private String telephone;

    @OneToMany(mappedBy = "agence", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Vehicule> vehicules = new HashSet<>();

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    private Set<Employe> employes = new HashSet<>();

    public void addVehicule(Vehicule v) {
        vehicules.add(v);
        v.setAgence(this);
    }
}