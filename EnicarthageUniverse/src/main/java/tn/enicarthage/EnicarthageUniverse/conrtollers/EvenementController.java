package tn.enicarthage.EnicarthageUniverse.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EnicarthageUniverse.entities.Evenement;
import tn.enicarthage.EnicarthageUniverse.services.EvenementService;

import java.util.List;

@RestController
@RequestMapping("/api/evenements")
@RequiredArgsConstructor
public class EvenementController {

    @Autowired
    private EvenementService evenementService;

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
    }}