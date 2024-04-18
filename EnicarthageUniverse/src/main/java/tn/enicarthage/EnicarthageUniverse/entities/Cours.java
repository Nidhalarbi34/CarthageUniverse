package tn.enicarthage.EnicarthageUniverse.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Administrateur administrateur;

    //@OneToMany(mappedBy = "cours", cascade = CascadeType.ALL)
    //private List<Commentaire> commentaires = new ArrayList<>();

    // Getters and setters
}

