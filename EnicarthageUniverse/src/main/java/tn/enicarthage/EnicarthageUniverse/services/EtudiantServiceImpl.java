package tn.enicarthage.EnicarthageUniverse.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;
import tn.enicarthage.EnicarthageUniverse.repsitories.EtudiantRepository;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnicarthageUniverse.Utils.JWTUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static tn.enicarthage.EnicarthageUniverse.Utils.JWTUtils.generateToken;

@Service
public class EtudiantServiceImpl implements EtudiantService {
    @Autowired
    EtudiantRepository etudiantRepository ;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public List<Etudiant> afficherEtudiant() {
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
    public Object login(Etudiant etudiant) {
        // Verify the user's credentials
        Etudiant etd = etudiantRepository.findEtudiantByEmail(etudiant.getEmail());

        if (etd == null) {
            throw new IllegalArgumentException("this email does not exist");
        } else if (!bCryptPasswordEncoder.matches(etudiant.getMdp(), etd.getMdp())) {
            throw new IllegalArgumentException("Wrong password");
        }  else {
            HashMap<String, Object> response = new HashMap<>();
            response.put("user", etd);
            response.put("token", generateToken(etd.getMdp()));
            return response;
        }


    }

}
