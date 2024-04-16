package tn.enicarthage.EnicarthageUniverse.repsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.enicarthage.EnicarthageUniverse.entities.Evenement;


import java.util.List;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    List<Evenement> findAllByOrderByDateAsc();
}