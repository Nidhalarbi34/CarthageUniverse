package tn.enicarthage.EnicarthageUniverse.repsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EnicarthageUniverse.entities.Note;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByMatiere(String matiere);

    List<Note> findByEtudiantId(Long id);
}