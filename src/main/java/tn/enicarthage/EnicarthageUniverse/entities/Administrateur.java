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
@DiscriminatorValue("ADMINISTRATEUR")
public class Administrateur extends User {
    private String poste;
    @Temporal(TemporalType.DATE)
    private Date dateCreationCompte;

    private String informationsContact;

}