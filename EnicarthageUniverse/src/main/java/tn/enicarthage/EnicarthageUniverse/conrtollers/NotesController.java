package tn.enicarthage.EnicarthageUniverse.conrtollers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EnicarthageUniverse.Requests.NoteRequest;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;
import tn.enicarthage.EnicarthageUniverse.entities.Note;
import tn.enicarthage.EnicarthageUniverse.services.NoteService;

import java.util.List;
import java.util.Map;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/notes")
public class NotesController {

    private final NoteService noteService;

    @Autowired
    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Note>> getNotesByEtudiantId(@PathVariable("id") Long id) {
        List<Note> notes = noteService.getNotesByEtudiantId(id);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/average-score")
    public ResponseEntity<Map<String, Double>> calculateAverageScoreByMatiere() {
        Map<String, Double> averageScores = noteService.calculateAverageScoreByMatiere();
        return ResponseEntity.ok(averageScores);
    }


    @GetMapping("/average-score-by-student")
    public ResponseEntity<Map<Long, Double>> calculateAverageScoreByStudent() {
        Map<Long, Double> averageScores = noteService.calculateAverageScoreByStudent();
        return new ResponseEntity<>(averageScores, HttpStatus.OK);
    }


    @GetMapping("/topStudentsByMatiere")
    public ResponseEntity<Map<String, Note>> getTopStudentsByMatiere() {
        Map<String, Note> topStudents = noteService.getTopStudentByMatiere();
        return new ResponseEntity<>(topStudents, HttpStatus.OK);
    }



    @GetMapping("/top")
    public Etudiant getTopStudentAcrossAllMatieres() {
        return noteService.getTopStudentAcrossAllMatieres();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNoteForStudentWithId1(@RequestBody NoteRequest request) {
        // Call the service method to add the note
        noteService.addNoteForStudentWithId1(request.getIdEtud(),request.getMatiere(), request.getValue());

        return new ResponseEntity<>("Note added successfully for student with ID 1", HttpStatus.CREATED);
    }

}
