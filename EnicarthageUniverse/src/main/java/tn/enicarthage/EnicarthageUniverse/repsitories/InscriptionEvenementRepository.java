package tn.enicarthage.EnicarthageUniverse.repsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EnicarthageUniverse.entities.InscriptionEvenement;

import java.util.List;

public interface InscriptionEvenementRepository extends JpaRepository<InscriptionEvenement, Long> {
    List<InscriptionEvenement> findByEvenementIdAndInteresse(Long evenementId, boolean interesse);
}
