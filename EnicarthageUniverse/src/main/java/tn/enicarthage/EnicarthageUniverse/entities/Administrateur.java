package tn.enicarthage.EnicarthageUniverse.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter


@DiscriminatorValue("ADMIN")
public class Administrateur extends User {
    private String poste;
    private Date dateCreationCompte;
    private String informationsContact;

    // Getters and setters
}
