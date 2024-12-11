package tn.esprit.eventsproject.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.repositories.EventRepository;

import java.time.LocalDate;

@SpringBootTest
public class EventServicesImplTestWithoutMockito {

    @Autowired
    private EventServicesImpl eventServices;

    @Autowired
    private EventRepository eventRepository;

    @Test
    void testEventCreationAndParticipantAffectation() {
        Participant participant = new Participant();
        participant.setNom("John");
        participant.setPrenom("Doe");

        Event event = new Event();
        event.setDescription("Company Retreat");
        event.setDateDebut(LocalDate.now());
        event.setDateFin(LocalDate.now().plusDays(2));

        // Assuming addParticipant and addAffectEvenParticipant methods handle the persistence
        participant = eventServices.addParticipant(participant);
        event = eventServices.addAffectEvenParticipant(event);

        assertThat(event.getParticipants()).isNotEmpty();
        assertThat(event.getParticipants().iterator().next().getNom()).isEqualTo("John");
    }
}
