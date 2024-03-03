package enicar.grpc.backend.Entites;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter


@DiscriminatorValue("ADMIN")
public class Administrateur extends Utilisateur {

}