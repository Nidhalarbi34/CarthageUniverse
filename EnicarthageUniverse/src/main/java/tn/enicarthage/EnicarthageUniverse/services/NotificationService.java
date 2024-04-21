package tn.enicarthage.EnicarthageUniverse.services;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;
import tn.enicarthage.EnicarthageUniverse.entities.Notification;
import tn.enicarthage.EnicarthageUniverse.repsitories.EtudiantRepository;
import tn.enicarthage.EnicarthageUniverse.repsitories.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;

    // Créer une nouvelle notification
    public Notification creerNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // Marquer une notification comme lue
    public Notification marquerNotificationLue(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElse(null);
        if (notification != null) {
            notification.setLue(true);
            return notificationRepository.save(notification);
        }
        return null;
    }

    public List<Notification> obtenirNotificationsEtudiantParDate(Long etudiantId) {
        return notificationRepository.findByEtudiantIdOrderByDateCreationAsc(etudiantId);
    }


    public List<Notification> obtenirNotificationsAdmin(Long adminId) {
        return notificationRepository.findByAdminId(adminId);
    }


    public List<Notification> obtenirNotificationsEtudiantEntreDates(Long etudiantId, LocalDateTime startDate, LocalDateTime endDate) {
        return notificationRepository.findByEtudiantIdAndDateCreationBetween(etudiantId, startDate, endDate);
    }


    public List<Notification> obtenirNotificationsAdminParDate(LocalDateTime dateCreation, Long adminId) {
        return notificationRepository.findByDateCreationAndAdminId(dateCreation, adminId);
    }


    public void supprimerNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }
    public void sendNotificationToLevel(String message, int niveau) {
        EmailSenderService emailService = null;
        List<Etudiant> etudiants = etudiantRepository.findEtudiantByAnneeEtudes(niveau);
        Notification notification = new Notification(message);
        notificationRepository.save(notification);

        for (Etudiant etudiant : etudiants) {
            emailService.sendSimpleEmail(etudiant.getEmail(), "Notification pour " + niveau, message);
        }
    }
}