package tn.enicarthage.EnicarthageUniverse.services;

import tn.enicarthage.EnicarthageUniverse.entities.Cours;

import java.util.List;

public interface CoursService {
    public List<Cours> getAllCourses();
   public  Cours getCourseById(Long id);
    public  List<Cours> getCourseByTitre(String titre);
  public  Cours createCourse(Cours cours);
   public Cours updateCourse(Long id, Cours cours);
  public  void deleteCourse(Long id);
}

