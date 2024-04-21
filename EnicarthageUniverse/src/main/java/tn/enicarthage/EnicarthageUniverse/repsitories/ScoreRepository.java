package tn.enicarthage.EnicarthageUniverse.repsitories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.enicarthage.EnicarthageUniverse.entities.Matieres;
import tn.enicarthage.EnicarthageUniverse.entities.Matieres;

import java.util.List;
@Repository
public interface ScoreRepository extends JpaRepository<Matieres, Long> {
    List<Matieres> findByStudentId(Long studentId);
}