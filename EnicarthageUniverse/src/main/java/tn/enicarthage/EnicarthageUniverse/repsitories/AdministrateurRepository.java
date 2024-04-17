package tn.enicarthage.EnicarthageUniverse.repsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.enicarthage.EnicarthageUniverse.entities.Administrateur;

@Repository

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
    Administrateur findAdministrateurByEmail(String email);
}
