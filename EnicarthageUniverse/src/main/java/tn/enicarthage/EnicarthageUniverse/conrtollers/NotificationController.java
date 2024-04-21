package tn.enicarthage.EnicarthageUniverse.conrtollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EnicarthageUniverse.entities.Notification;
import tn.enicarthage.EnicarthageUniverse.services.NotificationService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    @PostMapping
    public ResponseEntity<Notification> creerNotification(@RequestBody Notification notification) {
        Notification nouvelleNotification = notificationService.creerNotification(notification);
        return new ResponseEntity<>(nouvelleNotification, HttpStatus.CREATED);
    }


    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Notification>> obtenirNotificationsEtudiantParDate(@PathVariable Long etudiantId) {
        List<Notification> notifications = notificationService.obtenirNotificationsEtudiantParDate(etudiantId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }


    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<Notification>> obtenirNotificationsAdmin(@PathVariable Long adminId) {
        List<Notification> notifications = notificationService.obtenirNotificationsAdmin(adminId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }


    @GetMapping("/etudiant/{etudiantId}/dates")
    public ResponseEntity<List<Notification>> obtenirNotificationsEtudiantEntreDates(
            @PathVariable Long etudiantId,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<Notification> notifications = notificationService.obtenirNotificationsEtudiantEntreDates(etudiantId, startDate, endDate);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }


    @GetMapping("/admin/{adminId}/date")
    public ResponseEntity<List<Notification>> obtenirNotificationsAdminParDate(
            @RequestParam LocalDateTime dateCreation,
            @PathVariable Long adminId) {
        List<Notification> notifications = notificationService.obtenirNotificationsAdminParDate(dateCreation, adminId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @PatchMapping("/{notificationId}/lue")
    public ResponseEntity<Notification> marquerNotificationLue(@PathVariable Long notificationId) {
        Notification notification = notificationService.marquerNotificationLue(notificationId);
        if (notification != null) {
            return new ResponseEntity<>(notification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> supprimerNotification(@PathVariable Long notificationId) {
        notificationService.supprimerNotification(notificationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/send-to-level")
    public void sendNotificationToLevel(@RequestParam int niveau, @RequestBody String message) {
        notificationService.sendNotificationToLevel(message, niveau);
    }
}