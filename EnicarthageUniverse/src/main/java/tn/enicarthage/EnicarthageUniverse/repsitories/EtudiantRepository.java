package tn.enicarthage.EnicarthageUniverse.repsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
