package tn.enicarthage.EnicarthageUniverse.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

   // @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Notes> subjectScores;

    @Temporal(TemporalType.DATE)
    private Date dateCreationCompte;

    private String informationsContact;
    public Etudiant(Long studentId) {
        this.matricule=studentId.toString();
    }


}
