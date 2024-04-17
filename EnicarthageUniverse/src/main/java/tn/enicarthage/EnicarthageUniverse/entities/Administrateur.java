package tn.enicarthage.EnicarthageUniverse.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter


@DiscriminatorValue("ADMIN")
public class Administrateur extends User {
    private String poste;

    @Temporal(TemporalType.DATE)

    private Date dateCreationCompte;
    private String informationsContact;

    // Getters and setters
}
