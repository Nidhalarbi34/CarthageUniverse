package tn.enicarthage.EnicarthageUniverse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;
import tn.enicarthage.EnicarthageUniverse.repsitories.EtudiantRepository;
import tn.enicarthage.EnicarthageUniverse.services.EmailSenderService;

import javax.mail.MessagingException;
import java.util.Date;

@SpringBootApplication
public class EnicarthageUniverseApplication {



	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EnicarthageUniverseApplication.class, args);
		EtudiantRepository etudiantRepository = context.getBean(EtudiantRepository.class);


	}
	/*@Autowired
	private EmailSenderService senderService;
	public static void main(String[] args) {
		SpringApplication.run(EnicarthageUniverseApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		senderService.sendSimpleEmail("alnidh9@gmail.com",
				"This is email subject",
				"This is email body");

	}*/
}
