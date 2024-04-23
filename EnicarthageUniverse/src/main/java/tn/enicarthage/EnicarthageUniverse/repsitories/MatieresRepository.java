package tn.enicarthage.EnicarthageUniverse.repsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.enicarthage.EnicarthageUniverse.entities.Matieres;
@Repository
public interface MatieresRepository extends JpaRepository<Matieres, Integer> {
}
