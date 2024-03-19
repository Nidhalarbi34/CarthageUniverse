package tn.enicarthage.EnicarthageUniverse.services;

import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;

import java.util.List;
import java.util.Optional;

public interface EtudiantService {
    public Etudiant ajouterEtudiant(Etudiant admin );
    public Etudiant modifierEtudiant(Etudiant admin);
    public void   supprimerEtudiant(Long id );
    public List<Etudiant> afficheEtudiant();
    public Optional<Etudiant> afficherEtudiantById(Long id);
    //public void inscrireCours(Cours cours) ;
    public void consulterRessourcesEducatives();
    //public Map<Cours, Double> consulterNotes();
    public void consulterNotifications();
    public void consulterStatistiquesPersonnelles();
}
