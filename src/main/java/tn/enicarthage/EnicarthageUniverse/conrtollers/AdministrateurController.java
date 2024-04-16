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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Administrateur ajoutAdministrateur(@RequestBody Administrateur administrateur){
        administrateur.setMdp(this.bCryptPasswordEncoder.encode(administrateur.getMdp()));
        Administrateur  savedUser = administrateurRepository.save(administrateur);
        return administrateurService.ajouterAdministrateur(administrateur);

    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public void suppAdministrateur(@PathVariable("id") long id){
        administrateurService.supprimerAdministrateur(id);

    }
    @RequestMapping(method = RequestMethod.GET )
    public List<Administrateur> afficherAdministrateur(){
        return administrateurService.afficherAdministrateur();

    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Administrateur> getAdministrateurById(@PathVariable("id") long id){

        Optional<Administrateur> administrateur = administrateurService.afficherAdministrateurById(id);
        return administrateur;
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
                System.out.println("hhh");
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