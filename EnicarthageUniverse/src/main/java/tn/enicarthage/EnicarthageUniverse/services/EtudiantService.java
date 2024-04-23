package tn.enicarthage.EnicarthageUniverse.services;

import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;

import java.util.List;
import java.util.Optional;

public interface EtudiantService {
    public Etudiant ajouterEtudiant(Etudiant etudiant );
    public Etudiant modifierEtudiant(Etudiant etudiant);
    public void   supprimerEtudiant(Long id );
    public List<Etudiant> afficherEtudiant();
    public Optional<Etudiant> afficherEtudiantById(Long id);

    //public void inscrireCours(Cours cours) ;
    public void consulterRessourcesEducatives();
    //public Map<Cours, Double> consulterNotes();
    public void consulterNotifications();
    public void consulterStatistiquesPersonnelles();
    public Object login(Etudiant etudiant);

}
