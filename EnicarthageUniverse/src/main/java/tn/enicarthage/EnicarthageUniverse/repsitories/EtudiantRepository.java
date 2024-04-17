package tn.enicarthage.EnicarthageUniverse.repsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;

import java.util.List;

@Repository

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Etudiant findEtudiantByEmail(String email);
    List<Etudiant> findEtudiantBySpecialisationAndAnneeEtudes(String specialisation , int anneeEtudes);
}
