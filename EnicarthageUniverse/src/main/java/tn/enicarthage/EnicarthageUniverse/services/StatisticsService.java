package tn.enicarthage.EnicarthageUniverse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;
import tn.enicarthage.EnicarthageUniverse.entities.Matieres;
import tn.enicarthage.EnicarthageUniverse.repsitories.EtudiantRepository;
import tn.enicarthage.EnicarthageUniverse.entities.Notes;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
    private final EtudiantRepository etudiantRepository;

    @Autowired
    public StatisticsService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public Map<Matieres, Float> getAllNotes(Etudiant etudiant) {
        Map<Matieres, Float> allNotes = new HashMap<>();
        List<Notes> subjectScores = etudiant.getSubjectScores();

        for (Notes score : subjectScores) {
            Matieres subject = score.getMatiere();
            float currentScore = score.getScore();

            // Check if the subject already exists in the map
            if (allNotes.containsKey(subject)) {
                // If yes, update the score by adding the current score to the existing score
                float existingScore = allNotes.get(subject);
                allNotes.put(subject, existingScore + currentScore);
            } else {
                // If no, add the subject and its score to the map
                allNotes.put(subject, currentScore);
            }
        }

        return allNotes;
    }



    public Map<Matieres, Float> calculateAverageScores(List<Etudiant> students) {
        Map<Matieres, Float> totalScores = new HashMap<>();
        Map<Matieres, Integer> subjectCounts = new HashMap<>();

        // Iterate over each student
        for (Etudiant student : students) {
            // Iterate over the subject scores of the student
            for (Notes score : student.getSubjectScores()) {
                Matieres subject = score.getMatieres();
                float currentScore = score.getScore();

                // Update total score for the subject
                totalScores.put(subject, totalScores.getOrDefault(subject, 0f) + currentScore);
                // Update subject count
                subjectCounts.put(subject, subjectCounts.getOrDefault(subject, 0) + 1);
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






    public List<Etudiant> getAllStudents() {
        return etudiantRepository.findAll();
    }

    public Map<Matieres, Float> calculateAllNotes() {
        List<Etudiant> students = etudiantRepository.findAll();
        Map<Matieres, Float> allNotes = new HashMap<>();

        for (Etudiant student : students) {
            for (Notes score : student.getSubjectScores()) {
                Matieres subject = score.getMatiere();
                float currentScore = score.getScore();

                // Check if the subject already exists in the map
                if (allNotes.containsKey(subject)) {
                    // If yes, update the score by adding the current score to the existing score
                    float existingScore = allNotes.get(subject);
                    allNotes.put(subject, existingScore + currentScore);
                } else {
                    // If no, add the subject and its score to the map
                    allNotes.put(subject, currentScore);
                }
            }
        }

        return allNotes;
    }


    public Map<Etudiant, Float> calculateTotalScores(List<Etudiant> students) {
        Map<Etudiant, Float> totalScores = new HashMap<>();

        for (Etudiant student : students) {
            float totalScore = 0f;
            for (Notes score : student.getSubjectScores()) {
                totalScore += score.getScore();
            }
            totalScores.put(student, totalScore);
        }

        return totalScores;
    }


    public List<Etudiant> findTopStudentsByTotalScore(List<Etudiant> students, int n) {
        Map<Etudiant, Float> totalScores = calculateTotalScores(students);

        return totalScores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    public List<Etudiant> findTopStudentsByAverageScore(List<Etudiant> students, int n) {
        Map<Matieres, Float> averageScores = calculateAverageScores(students);

        // Calculate average score for each student
        Map<Etudiant, Float> studentAverageScores = new HashMap<>();
        for (Etudiant student : students) {
            float totalScore = 0f;
            for (Notes score : student.getSubjectScores()) {
                totalScore += score.getScore();
            }
            float averageScore = totalScore / student.getSubjectScores().size();
            studentAverageScores.put(student, averageScore);
        }

        return studentAverageScores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }




}