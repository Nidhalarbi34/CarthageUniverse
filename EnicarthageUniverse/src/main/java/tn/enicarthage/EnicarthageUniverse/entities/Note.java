package tn.enicarthage.EnicarthageUniverse.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    private String matiere;

    private Float value;

    // Constructors, getters, and setters
}
