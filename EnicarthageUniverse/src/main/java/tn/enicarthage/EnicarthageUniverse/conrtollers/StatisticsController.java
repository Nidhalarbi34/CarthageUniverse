package tn.enicarthage.EnicarthageUniverse.conrtollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;
import tn.enicarthage.EnicarthageUniverse.entities.Matieres;
import tn.enicarthage.EnicarthageUniverse.services.StatisticsService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/all-notes")
    public ResponseEntity<Map<Matieres, Float>> getAllNotes() {
        // Retrieve all students
        List<Etudiant> students = statisticsService.getAllStudents();

        // Calculate and return all subject scores
        Map<Matieres, Float> allNotes = statisticsService.calculateAllNotes();
        return ResponseEntity.ok(allNotes);
    }

    @GetMapping("/average-scores")
    public ResponseEntity<Map<Matieres, Float>> getAverageScores() {
        // Retrieve all students
        List<Etudiant> students = statisticsService.getAllStudents();

        // Calculate and return average scores
        Map<Matieres, Float> averageScores = statisticsService.calculateAverageScores(students);
        return ResponseEntity.ok(averageScores);
    }



    @GetMapping("/topTotalScores")
    public List<Etudiant> getTopStudentsByTotalScore(@RequestParam int n) {
        List<Etudiant> students = statisticsService.getAllStudents();
        return statisticsService.findTopStudentsByTotalScore(students, n);
    }

    @GetMapping("/topAverageScores")
    public List<Etudiant> getTopStudentsByAverageScore(@RequestParam int n) {
        List<Etudiant> students = statisticsService.getAllStudents();
        return statisticsService.findTopStudentsByAverageScore(students, n);
    }



}