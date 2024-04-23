package tn.enicarthage.EnicarthageUniverse.services;

import tn.enicarthage.EnicarthageUniverse.entities.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Contact ajouterContact(Contact contact);
    Contact modifierContact(Contact contact);
    void supprimerContact(Long id);
    List<Contact> afficherContact();
    Optional<Contact> afficheContactById(Long id );

}
