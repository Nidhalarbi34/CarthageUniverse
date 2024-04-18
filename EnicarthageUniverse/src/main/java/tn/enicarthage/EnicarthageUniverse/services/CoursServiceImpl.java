package tn.enicarthage.EnicarthageUniverse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnicarthageUniverse.entities.Cours;
import tn.enicarthage.EnicarthageUniverse.repsitories.CoursRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CoursServiceImpl implements CoursService {

    private final CoursRepository coursRepository;

    @Autowired
    public CoursServiceImpl(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    @Override
    public List<Cours> getAllCourses() {
        return coursRepository.findAll();
    }

    @Override
    public Cours getCourseById(Long id) {
        Optional<Cours> optionalCours = coursRepository.findById(id);
        return optionalCours.orElse(null);
    }

    @Override
    public Cours createCourse(Cours cours) {
        return coursRepository.save(cours);
    }

    @Override
    public Cours updateCourse(Long id, Cours cours) {
        if (coursRepository.existsById(id)) {
            cours.setId(id);
            return coursRepository.save(cours);
        }
        return null;
    }

    @Override
    public void deleteCourse(Long id) {
        coursRepository.deleteById(id);
    }
}
