package tn.enicarthage.EnicarthageUniverse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import tn.enicarthage.EnicarthageUniverse.services.EmailSenderService;

import javax.mail.MessagingException;

@SpringBootApplication
public class EnicarthageUniverseApplication {
	@Autowired
	private EmailSenderService senderService;
	public static void main(String[] args) {
		SpringApplication.run(EnicarthageUniverseApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		senderService.sendSimpleEmail("alnidh9@gmail.com",
				"This is email subject",
				"This is email body");

	}
}
