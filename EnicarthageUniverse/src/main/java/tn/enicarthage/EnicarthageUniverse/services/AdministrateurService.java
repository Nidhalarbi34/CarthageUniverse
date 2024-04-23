package tn.enicarthage.EnicarthageUniverse.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.enicarthage.EnicarthageUniverse.entities.Administrateur;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;
import tn.enicarthage.EnicarthageUniverse.repsitories.AdministrateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static tn.enicarthage.EnicarthageUniverse.Utils.JWTUtils.generateToken;

@Service
public class AdministrateurService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AdministrateurRepository administrateurRepository;

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
    public Object login(Administrateur administrateur) {
        // Verify the user's credentials
        Administrateur etd = administrateurRepository.findAdministrateurByEmail(administrateur.getEmail());

        if (etd == null) {
            throw new IllegalArgumentException("this email does not exist");
        } else if (!bCryptPasswordEncoder.matches(administrateur.getMdp(), etd.getMdp())) {
            throw new IllegalArgumentException("Wrong password");
        }  else {
            HashMap<String, Object> response = new HashMap<>();
            response.put("user", etd);
            response.put("token", generateToken(etd.getMdp()));
            return response;
        }


    }
}