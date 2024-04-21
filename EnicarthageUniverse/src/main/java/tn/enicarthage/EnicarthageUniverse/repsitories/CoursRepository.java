package tn.enicarthage.EnicarthageUniverse.repsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EnicarthageUniverse.entities.Cours;

import java.util.List;

public interface CoursRepository extends JpaRepository<Cours, Long> {
    public List<Cours> findByTitreContainingIgnoreCase(String titre);
}
