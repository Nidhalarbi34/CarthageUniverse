package tn.enicarthage.EnicarthageUniverse.repsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EnicarthageUniverse.entities.Cours;

public interface CoursRepository extends JpaRepository<Cours, Long> {
}
