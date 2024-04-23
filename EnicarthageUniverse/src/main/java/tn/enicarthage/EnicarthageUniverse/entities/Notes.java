package tn.enicarthage.EnicarthageUniverse.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matieres matiere;

    private Float score;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")  // Specifies the column in 'Notes' table that joins to the 'Etudiant' table
    private Etudiant etudiant;

}