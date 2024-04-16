package tn.enicarthage.EnicarthageUniverse.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("ETUDIANT")
public class Etudiant extends User {
    private String matricule;
    private String specialisation;
    private int anneeEtudes;

   //@ManyToMany
    //private List<Cours> coursInscrits;
   // private Map<Cours, Double> notes;

    private Date dateCreationCompte;
    private String informationsContact;

}
