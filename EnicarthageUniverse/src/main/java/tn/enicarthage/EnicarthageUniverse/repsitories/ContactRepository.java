package tn.enicarthage.EnicarthageUniverse.repsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EnicarthageUniverse.entities.Contact;
import tn.enicarthage.EnicarthageUniverse.entities.Cours;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
