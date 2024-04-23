package tn.enicarthage.EnicarthageUniverse.services;

import lombok.RequiredArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnicarthageUniverse.entities.Cours;
import tn.enicarthage.EnicarthageUniverse.entities.Evenement;
import tn.enicarthage.EnicarthageUniverse.repsitories.EvenementRepository;

import java.util.List;

@Service

public class EvenementService {
    @Autowired
    private EvenementRepository evenementRepository;

    public Evenement creerEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }


    public Evenement obtenirEvenementParId(Long id) {
        return evenementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));
    }


    public Evenement modifierEvenement(Long id, Evenement evenementModifie) {
        Evenement evenementExist = evenementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));


        evenementExist.setTitre(evenementModifie.getTitre());
        evenementExist.setDescription(evenementModifie.getDescription());
        evenementExist.setDate(evenementModifie.getDate());


        return evenementRepository.save(evenementExist);
    }


    public void supprimerEvenement(Long id) {
        evenementRepository.deleteById(id);
    }


    public List<Evenement> afficherTousLesEvenements() {
        return evenementRepository.findAllByOrderByDateAsc();
    }
    public  List<Evenement> getEvenementByTitre(String titre){
        return evenementRepository.findByTitreContainingIgnoreCase(titre);
    }


}