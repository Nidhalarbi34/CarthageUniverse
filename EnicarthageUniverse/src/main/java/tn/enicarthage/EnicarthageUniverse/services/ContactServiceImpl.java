package tn.enicarthage.EnicarthageUniverse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnicarthageUniverse.entities.Contact;
import tn.enicarthage.EnicarthageUniverse.repsitories.ContactRepository;

import java.util.List;
import java.util.Optional;
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepositery;
    @Override
    public Contact ajouterContact(Contact contact){ return  contactRepositery.save(contact);}
    public Contact modifierContact(Contact contact){ return contactRepositery.save(contact);}
    public void supprimerContact(Long id){ contactRepositery.deleteById(id);}
    public List<Contact> afficherContact(){ return contactRepositery.findAll();}
    @Override
    public Optional<Contact> afficheContactById(Long id) {
        return contactRepositery.findById(id);
    }
}
