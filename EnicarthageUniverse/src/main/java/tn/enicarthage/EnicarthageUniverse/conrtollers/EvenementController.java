package tn.enicarthage.EnicarthageUniverse.conrtollers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EnicarthageUniverse.entities.Cours;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;
import tn.enicarthage.EnicarthageUniverse.entities.Evenement;
import tn.enicarthage.EnicarthageUniverse.entities.InscriptionEvenement;
import tn.enicarthage.EnicarthageUniverse.services.EvenementService;
import tn.enicarthage.EnicarthageUniverse.services.InscriptionEvenementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/evenements")
@RequiredArgsConstructor
public class EvenementController {

    @Autowired
    private EvenementService evenementService;
    @Autowired
    private InscriptionEvenementService inscriptionService;

    @PostMapping("/marquer-interet")
    public InscriptionEvenement marquerInteret(@RequestParam Long etudiantId, @RequestParam Long evenementId, @RequestParam boolean interesse) {
        return inscriptionService.marquerInteret(etudiantId, evenementId, interesse);
    }

    @GetMapping("/{evenementId}/etudiants-interesses")
    public List<Etudiant> getEtudiantsInteresses(@PathVariable Long evenementId) {
        List<InscriptionEvenement> inscriptions = inscriptionService.getEtudiantsInteressesParEvenement(evenementId);
        return inscriptions.stream().map(InscriptionEvenement::getEtudiant).collect(Collectors.toList());
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Evenement creerEvenement(@RequestBody Evenement evenement) {
        return evenementService.creerEvenement(evenement);
    }


    @GetMapping("/{id}")
    public Evenement obtenirEvenementParId(@PathVariable Long id) {
        return evenementService.obtenirEvenementParId(id);
    }

    // Modifier un événement existant
    @PutMapping("/{id}")
    public Evenement modifierEvenement(@PathVariable Long id, @RequestBody Evenement evenementModifie) {
        return evenementService.modifierEvenement(id, evenementModifie);
    }


    @DeleteMapping("/{id}")
    public void supprimerEvenement(@PathVariable Long id) {
        evenementService.supprimerEvenement(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Evenement> afficherTousLesEvenements() {
        return evenementService.afficherTousLesEvenements();
    }
    @GetMapping("/search")
    public List<Evenement> getEvenementByTitre(@RequestParam String titre) {

        return evenementService.getEvenementByTitre(titre);
    }
}