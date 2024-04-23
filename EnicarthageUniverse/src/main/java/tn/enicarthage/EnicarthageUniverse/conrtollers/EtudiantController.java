package tn.enicarthage.EnicarthageUniverse.conrtollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;
import tn.enicarthage.EnicarthageUniverse.repsitories.EtudiantRepository;
import tn.enicarthage.EnicarthageUniverse.services.EtudiantService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping(value ="/etudiant")
public class EtudiantController {
    @Autowired
    EtudiantService etudiantService;
    private EtudiantRepository etudiantRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired

    public EtudiantController(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;



    }
    @RequestMapping(method =  RequestMethod.POST )
    public ResponseEntity<Object> ajouterEtudiant(@RequestBody Etudiant etudiant) {
        try {
            etudiant.setMdp(this.bCryptPasswordEncoder.encode(etudiant.getMdp()));
            Etudiant savedEtudiant = etudiantRepository.save(etudiant);
            etudiantService.ajouterEtudiant(etudiant);
            log.info("Étudiant créé avec succès : {}", savedEtudiant.getEmail());
            return ResponseEntity.ok(savedEtudiant);
        } catch (Exception e) {
            log.error("Erreur lors de la création de l'étudiant : {}", etudiant.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la création de l'étudiant");
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public ResponseEntity<Object> suppEtudiant(@PathVariable("id") long id) {
        try {
            etudiantService.supprimerEtudiant(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Erreur lors de la suppression de l'étudiant avec l'ID : {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la suppression de l'étudiant");
        }
    }
    @RequestMapping(method = RequestMethod.GET )
    public ResponseEntity<Object> afficherEtudiant() {
        try {
            List<Etudiant> etudiants = etudiantService.afficherEtudiant();
            return ResponseEntity.ok(etudiants);
        } catch (Exception e) {
            log.error("Erreur lors de l'affichage des étudiants", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'affichage des étudiants");
        }
    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getEtudiantById(@PathVariable("id") long id) {
        try {
            Optional<Etudiant> etudiant = etudiantService.afficherEtudiantById(id);
            if (etudiant.isPresent()) {
                return ResponseEntity.ok(etudiant.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de l'étudiant avec l'ID : {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la récupération de l'étudiant");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Etudiant user) {
        log.info("Attempting to log in user: {}", user.getEmail());
        Object data = etudiantService.login(user);
        HashMap<String, Object> response = (HashMap<String, Object>) data;
        if (response != null) {
            log.info("User logged in successfully: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            log.error("User login failed for: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    @PostMapping(path = "register")
    public ResponseEntity<Etudiant> addEtudiant(@RequestBody Etudiant etudiant) {
        etudiant.setMdp(this.bCryptPasswordEncoder.encode(etudiant.getMdp()));
        Etudiant savedUser = etudiantRepository.save(etudiant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
