package tn.enicarthage.EnicarthageUniverse.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.enicarthage.EnicarthageUniverse.entities.Administrateur;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;
import tn.enicarthage.EnicarthageUniverse.repsitories.AdministrateurRepository;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnicarthageUniverse.repsitories.EtudiantRepository;

import java.util.List;
import java.util.Optional;
@Service
public class AdministrateurService {
    @Autowired
    AdministrateurRepository administrateurRepository;
    @Autowired
    EtudiantRepository etudiantRepository ;

    public Administrateur ajouterAdministrateur(Administrateur admin) {
        return administrateurRepository.save(admin);
    }

    public Administrateur modifierAdministrateur(Administrateur admin) {
        return administrateurRepository.save(admin);
    }

    public void supprimerAdministrateur(Long id) {
        administrateurRepository.deleteById(id);

    }

    public List<Administrateur> afficherAdministrateur() {
        return administrateurRepository.findAll();
    }

    public Optional<Administrateur> afficherAdministrateurById(Long id) {
        return administrateurRepository.findById(id);
    }

}