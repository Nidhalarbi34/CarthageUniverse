package tn.enicarthage.EnicarthageUniverse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;
import tn.enicarthage.EnicarthageUniverse.entities.Evenement;
import tn.enicarthage.EnicarthageUniverse.entities.InscriptionEvenement;
import tn.enicarthage.EnicarthageUniverse.repsitories.EtudiantRepository;
import tn.enicarthage.EnicarthageUniverse.repsitories.EvenementRepository;
import tn.enicarthage.EnicarthageUniverse.repsitories.InscriptionEvenementRepository;

import java.util.List;

@Service
public class InscriptionEvenementService {
    @Autowired
    private InscriptionEvenementRepository inscriptionRepository;
    private EtudiantRepository etudiantRepository;
    private EvenementRepository evenementRepository;

    public InscriptionEvenement marquerInteret(Long etudiantId, Long evenementId, boolean interesse) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow(() -> new RuntimeException("Student not found"));
        Evenement evenement = evenementRepository.findById(evenementId).orElseThrow(() -> new RuntimeException("Event not found"));

        InscriptionEvenement inscription = new InscriptionEvenement(etudiant, evenement, interesse);
        return inscriptionRepository.save(inscription);
    }

    public List<InscriptionEvenement> getEtudiantsInteressesParEvenement(Long evenementId) {
        return inscriptionRepository.findByEvenementIdAndInteresse(evenementId, true);
    }
}
