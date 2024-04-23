package tn.enicarthage.EnicarthageUniverse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;

import tn.enicarthage.EnicarthageUniverse.entities.Matieres;
import tn.enicarthage.EnicarthageUniverse.repsitories.EtudiantRepository;
import tn.enicarthage.EnicarthageUniverse.entities.Notes;
import tn.enicarthage.EnicarthageUniverse.repsitories.NotesRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
    private final EtudiantRepository etudiantRepository;
    private final NotesRepository notesRepository;
    @Autowired
    public StatisticsService(EtudiantRepository etudiantRepository, NotesRepository notesRepository) {
        this.etudiantRepository = etudiantRepository;
        this.notesRepository = notesRepository;  // Initialize the repository
    }


    public Map<Matieres, Float> getAllNotes(Etudiant etudiant) {
        List<Notes> subjectScores = notesRepository.findByEtudiantId(etudiant.getId()); // Use the repository to get scores
        Map<Matieres, Float> allNotes = new HashMap<>();
        for (Notes score : subjectScores) {
            Matieres subject = score.getMatiere();
            float currentScore = score.getScore();
            allNotes.merge(subject, currentScore, Float::sum);
        }
        return allNotes;
    }


    public Map<Matieres, Float> calculateAverageScores(List<Etudiant> students) {
        Map<Matieres, Float> totalScores = new HashMap<>();
        Map<Matieres, Integer> subjectCounts = new HashMap<>();

        // Iterate over each student
        for (Etudiant student : students) {
            List<Notes> notes = notesRepository.findByEtudiantId(student.getId());
            // Iterate over the subject scores of the student
            for (Notes score : notes) {
                Matieres subject = score.getMatiere();
                float currentScore = score.getScore();

                // Update total score for the subject
                totalScores.merge(subject, currentScore, Float::sum);
                // Update subject count
                subjectCounts.merge(subject, 1, Integer::sum);
            }
        }

        // Calculate average score for each subject
        Map<Matieres, Float> averageScores = new HashMap<>();
        for (Matieres subject : totalScores.keySet()) {
            float totalScore = totalScores.getOrDefault(subject, 0f);
            int count = subjectCounts.getOrDefault(subject, 0);
            float averageScore = count > 0 ? totalScore / count : 0;
            averageScores.put(subject, averageScore);
        }

        return averageScores;
    }

    public Map<Etudiant, Float> calculateTotalScores(List<Etudiant> students) {
        Map<Etudiant, Float> totalScores = new HashMap<>();

        for (Etudiant student : students) {
            student.getSubjectScores().forEach(score ->
                    totalScores.merge(student, score.getScore(), Float::sum)
            );
        }

        return totalScores;
    }
    public List<Etudiant> findTopStudentsByTotalScore(List<Etudiant> students, int n) {
        return calculateTotalScores(students).entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    public List<Etudiant> getAllStudents() {
        return  etudiantRepository.findAll();
    }
}