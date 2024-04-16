package tn.enicarthage.EnicarthageUniverse.repsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
