package tn.enicarthage.EnicarthageUniverse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnicarthageUniverse.entities.Note;
import tn.enicarthage.EnicarthageUniverse.repsitories.NoteRepository;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotesByEtudiantId(Long id) {
        return noteRepository.findByEtudiantId(id);
    }

    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    // Add other CRUD methods as needed
}
