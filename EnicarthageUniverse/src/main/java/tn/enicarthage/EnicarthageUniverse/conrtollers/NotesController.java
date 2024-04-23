package tn.enicarthage.EnicarthageUniverse.conrtollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EnicarthageUniverse.entities.Note;
import tn.enicarthage.EnicarthageUniverse.services.NoteService;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    private final NoteService noteService;

    @Autowired
    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<Note>> getNotesByEtudiantId(@PathVariable Long id) {
        List<Note> notes = noteService.getNotesByEtudiantId(id);
        return ResponseEntity.ok(notes);
    }

    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        Note savedNote = noteService.addNote(note);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    // Add other endpoints as needed for CRUD operations
}
