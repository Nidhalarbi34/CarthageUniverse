package tn.enicarthage.EnicarthageUniverse.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;
import tn.enicarthage.EnicarthageUniverse.entities.Note;
import tn.enicarthage.EnicarthageUniverse.repsitories.EtudiantRepository;
import tn.enicarthage.EnicarthageUniverse.repsitories.NoteRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private  final EtudiantRepository etudiantRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository, EtudiantRepository etudiantRepository) {
        this.noteRepository = noteRepository;
        this.etudiantRepository = etudiantRepository;
    }

    public List<Note> getNotesByEtudiantId(Long id) {
        return noteRepository.findByEtudiantId(id);
    }

    public Map<String, Double> calculateAverageScoreByMatiere() {
        List<Note> allNotes = noteRepository.findAll();

        // Group notes by matiere and calculate average score for each
        Map<String, Double> averageScores = allNotes.stream()
                .collect(Collectors.groupingBy(Note::getMatiere,
                        Collectors.averagingDouble(Note::getValue)));

        // Return the map of matiere to average scores
        return averageScores;
        // Add other CRUD methods as needed
    }


    public Map<Long, Double> calculateAverageScoreByStudent() {
        List<Note> allNotes = noteRepository.findAll();

        // Group notes by student ID and calculate average score for each student
        return allNotes.stream()
                .collect(Collectors.groupingBy(
                        note -> note.getEtudiant().getId(),
                        Collectors.averagingDouble(Note::getValue)
                ));
    }

    public Note addNote(Note note) {
        return noteRepository.save(note);

    }


    public Map<String, Note> getTopStudentByMatiere() {
        Map<String, Note> topStudents = new HashMap<>();

        // Group notes by matiere
        Map<String, List<Note>> notesByMatiere = noteRepository.findAll().stream()
                .collect(Collectors.groupingBy(Note::getMatiere));

        // Iterate over each group
        for (Map.Entry<String, List<Note>> entry : notesByMatiere.entrySet()) {
            List<Note> notes = entry.getValue();
            // Sort notes by value in descending order
            notes.sort(Comparator.comparing(Note::getValue).reversed());
            // Get the top student for the matiere
            Note topStudent = notes.get(0);
            // Add the top student to the map
            topStudents.put(entry.getKey(), topStudent);
        }

        return topStudents;
    }



    public Etudiant getTopStudentAcrossAllMatieres() {
        List<Note> allNotes = noteRepository.findAll();

        // Map to store the total score for each student
        Map<Etudiant, Double> totalScoresByStudent = new HashMap<>();

        // Calculate total score for each student
        allNotes.forEach(note -> {
            Etudiant student = note.getEtudiant();
            double score = note.getValue();
            totalScoresByStudent.merge(student, score, Double::sum);
        });

        // Find the student with the highest total score
        Optional<Map.Entry<Etudiant, Double>> topStudentEntry = totalScoresByStudent.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        // Return the top student
        return topStudentEntry.map(Map.Entry::getKey).orElse(null);
    }


    public void addNoteForStudentWithId1(Long id,String matiere, Float value) {
        // Assuming etudiant with id=1 exists
        Etudiant etudiant = etudiantRepository.findById(id).get();

        // Create a new Note object
        Note note = new Note();
        note.setEtudiant(etudiant);
        note.setMatiere(matiere);
        note.setValue(value);

        // Save the Note using NoteRepository
        noteRepository.save(note);
    }




}