package tn.enicarthage.EnicarthageUniverse.conrtollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EnicarthageUniverse.entities.Administrateur;
import tn.enicarthage.EnicarthageUniverse.repsitories.AdministrateurRepository;
import tn.enicarthage.EnicarthageUniverse.services.AdministrateurService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping(value ="/admin")
public class AdministrateurController {
    @Autowired
    AdministrateurService administrateurService;
    private AdministrateurRepository administrateurRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired

    public AdministrateurController(AdministrateurRepository administrateurRepository) {
        this.administrateurRepository = administrateurRepository;



    }

    @RequestMapping(method =  RequestMethod.POST )

    public ResponseEntity<Object> ajoutAdministrateur(@RequestBody Administrateur administrateur) {
        try {
            administrateur.setMdp(this.bCryptPasswordEncoder.encode(administrateur.getMdp()));
            Administrateur savedAdministrateur = administrateurRepository.save(administrateur);
            administrateurService.ajouterAdministrateur(administrateur);
            log.info("Administrateur créé avec succès : {}", savedAdministrateur.getEmail());
            return ResponseEntity.ok(savedAdministrateur);
        } catch (Exception e) {
            log.error("Erreur lors de la création de l'administrateur : {}", administrateur.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la création de l'administrateur");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public ResponseEntity<Object> suppAdministrateur(@PathVariable("id") long id) {
        try {
            administrateurService.supprimerAdministrateur(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Erreur lors de la suppression de l'administrateur avec l'ID : {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la suppression de l'administrateur");
        }
    }
    @RequestMapping(method = RequestMethod.GET )
    public ResponseEntity<Object> afficherAdministrateur() {
        try {
            List<Administrateur> administrateurs = administrateurService.afficherAdministrateur();
            return ResponseEntity.ok(administrateurs);
        } catch (Exception e) {
            log.error("Erreur lors de l'affichage des administrateurs", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'affichage des administrateurs");
        }
    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getAdministrateurById(@PathVariable("id") long id) {
        try {
            Optional<Administrateur> administrateur = administrateurService.afficherAdministrateurById(id);
            if (administrateur.isPresent()) {
                return ResponseEntity.ok(administrateur.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de l'administrateur avec l'ID : {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la récupération de l'administrateur");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginAdministrateur(@RequestBody Administrateur administrateur) {
        System.out.println("in login-Administrateur"+administrateur);

        HashMap<String, Object> response = new HashMap<>();

        Administrateur userFromDB = administrateurRepository.findAdministrateurByEmail(administrateur.getEmail());
        System.out.println("userFromDB+Administrateur"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "Administrateur not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(administrateur.getMdp(), userFromDB.getMdp());
            System.out.println("compare"+compare);
            if (!compare) {
                response.put("message", "Administrateur not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else
            {
                String token = Jwts.builder()
                        .claim("data", userFromDB)
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);
                System.out.println("ok");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

        }

    }
    @PostMapping(path = "register")
    public ResponseEntity<Administrateur> addAdministrateur(@RequestBody Administrateur administrateur) {
        administrateur.setMdp(this.bCryptPasswordEncoder.encode(administrateur.getMdp()));
        Administrateur savedUser = administrateurRepository.save(administrateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}