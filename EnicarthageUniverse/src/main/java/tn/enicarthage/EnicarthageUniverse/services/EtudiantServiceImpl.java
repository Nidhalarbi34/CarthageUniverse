package tn.enicarthage.EnicarthageUniverse.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;
import tn.enicarthage.EnicarthageUniverse.repsitories.EtudiantRepository;

import java.util.List;
import java.util.Optional;

public class EtudiantServiceImpl implements EtudiantService {
    @Autowired
    EtudiantRepository etudiantRepository ;

    @Override
    public Etudiant ajouterEtudiant(Etudiant etudiant ) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant modifierEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public void  supprimerEtudiant(Long id) {
        etudiantRepository.deleteById(id);

    }

    @Override
    public List<Etudiant> afficheEtudiant() {
        return etudiantRepository.findAll();
    }

    @Override
    public Optional<Etudiant> afficherEtudiantById(Long id) {
        return etudiantRepository.findById(id);
    }

   /* @Override
    public void inscrireCours(Cours cours) {
        User etudiant = getCurrentUser();
        if (etudiant instanceof Etudiant) {
            List<Cours> coursInscrits = ((Etudiant) etudiant).getCoursInscrits();

            if (!coursInscrits.contains(cours)) {
                coursInscrits.add(cours);
                userRepository.save(etudiant);
            } else {
                System.out.println("L'étudiant est déjà inscrit à ce cours.");
            }
        } else {
            System.out.println("L'utilisateur n'est pas un étudiant.");
        }
    }*/
    @Override
    public void consulterRessourcesEducatives() {

    }

    @Override
    public void consulterNotifications() {

    }

    @Override
    public void consulterStatistiquesPersonnelles() {

    }
}
