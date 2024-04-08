package tn.enicarthage.EnicarthageUniverse.conrtollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;
import tn.enicarthage.EnicarthageUniverse.repsitories.EtudiantRepository;
import tn.enicarthage.EnicarthageUniverse.services.EtudiantService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Etudiant ajoutEtudiant(@RequestBody Etudiant etudiant){
        etudiant.setMdp(this.bCryptPasswordEncoder.encode(etudiant.getMdp()));
        Etudiant  savedUser = etudiantRepository.save(etudiant);
        return etudiantService.ajouterEtudiant(etudiant);

    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
//RequestBody:tekhdh vrb tabaathhom lel contrl kn sar c bon snn erreur
    public void suppEtudiant(@PathVariable("id") long id){
        etudiantService.supprimerEtudiant(id);

    }
    @RequestMapping(method = RequestMethod.GET )
    public List<Etudiant> afficherEtudiant(){
        return etudiantService.afficherEtudiant();

    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Etudiant> getEtudiantById(@PathVariable("id") long id){

        Optional<Etudiant> etudiant = etudiantService.afficherEtudiantById(id);
        return etudiant;
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginEtudiant(@RequestBody Etudiant etudiant) {
        System.out.println("in login-etudiant"+etudiant);

        HashMap<String, Object> response = new HashMap<>();

        Etudiant userFromDB = etudiantRepository.findEtudiantByEmail(etudiant.getEmail());
        System.out.println("userFromDB+etudiant"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "Etudiant not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(etudiant.getMdp(), userFromDB.getMdp());
            System.out.println("compare"+compare);
            if (!compare) {
                response.put("message", "Etudiant not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else
            {
                String token = Jwts.builder()
                        .claim("data", userFromDB)
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);
                System.out.println("hhh");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

        }

    }
    @PostMapping(path = "register")
    public ResponseEntity<Etudiant> addEtudiant(@RequestBody Etudiant etudiant) {
        etudiant.setMdp(this.bCryptPasswordEncoder.encode(etudiant.getMdp()));
        Etudiant savedUser = etudiantRepository.save(etudiant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
