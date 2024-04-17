package tn.enicarthage.EnicarthageUniverse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnicarthageUniverse.repsitories.CoursRepository;

@Service
public class CoursServiceImpl {
    @Autowired
    CoursRepository coursRepository ;
    //public void ajouterEtudiantcours(Long idEtudiant , Long idCours){coursRepository.}
}
