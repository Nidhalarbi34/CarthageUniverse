package tn.enicarthage.EnicarthageUniverse.repsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.enicarthage.EnicarthageUniverse.entities.Notification;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByEtudiantIdOrderByDateCreationAsc(Long etudiantId);


    List<Notification> findByAdminId(Long adminId);


    List<Notification> findByEtudiantIdAndDateCreationBetween(Long etudiantId, LocalDateTime startDate, LocalDateTime endDate);


    @Query("SELECT n FROM Notification n WHERE n.dateCreation = :dateCreation AND n.admin.id = :adminId")
    List<Notification> findByDateCreationAndAdminId(LocalDateTime dateCreation, Long adminId);


}