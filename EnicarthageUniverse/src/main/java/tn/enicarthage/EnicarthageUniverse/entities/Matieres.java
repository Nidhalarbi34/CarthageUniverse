package tn.enicarthage.EnicarthageUniverse.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Matieres{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false, length = 100)  // Définit les propriétés de la colonne pour la base de données
    private String nom;

    @Column(nullable = true)
    private String description;
}