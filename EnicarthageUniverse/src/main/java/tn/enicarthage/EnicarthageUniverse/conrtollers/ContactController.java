package tn.enicarthage.EnicarthageUniverse.conrtollers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EnicarthageUniverse.entities.Contact;
import tn.enicarthage.EnicarthageUniverse.repsitories.ContactRepository;
import tn.enicarthage.EnicarthageUniverse.services.ContactService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping(value ="/contact")
public class ContactController {
    @Autowired
    ContactService contactService;
    private ContactRepository contactRepositery;


    @Autowired

    public ContactController(ContactRepository contactRepositery) {
        this.contactRepositery = contactRepositery;



    }

    @RequestMapping(method =  RequestMethod.POST)
    public ResponseEntity<Object> ajoutContact(@RequestBody Contact contact) {
        try {
            Contact savedContact = contactRepositery.save(contact);
            contactService.ajouterContact(contact);
            log.info("Contact ajouté avec succès : {}",savedContact.getId());
            return ResponseEntity.ok(savedContact);
        } catch (Exception e) {
            log.error("Erreur lors de l'ajout du contact",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'ajout du contact");
        }
    }
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public ResponseEntity<Object> modifierContact(@PathVariable("id") Long id, @RequestBody Contact contact) {
        try {
            Contact newContact = contactService.modifierContact(contact);
            return ResponseEntity.ok(newContact);
        } catch (Exception e) {
            log.error("Erreur lors de la modification du contact avec l'ID : {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la modification du contact");
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public ResponseEntity<Object> suppContact(@PathVariable("id") long id) {
        try {
            contactService.supprimerContact(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Erreur lors de la suppression du contact avec l'ID : {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la suppression du contact");
        }
    }
    @RequestMapping(method = RequestMethod.GET )
    public ResponseEntity<Object> afficherContact() {
        try {
            List<Contact> contacts = contactService.afficherContact();
            return ResponseEntity.ok(contacts);
        } catch (Exception e) {
            log.error("Erreur lors de l'affichage des contacts", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'affichage des contacts");
        }
    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getContactById(@PathVariable("id") long id) {
        try {
            Optional<Contact> contact = contactService.afficheContactById(id);
            if (contact.isPresent()) {
                return ResponseEntity.ok(contact.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du contact avec l'ID : {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la récupération du contact");
        }
    }
}