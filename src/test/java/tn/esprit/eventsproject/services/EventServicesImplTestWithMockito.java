package tn.esprit.eventsproject.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

public class EventServicesImplTestWithMockito {

    @InjectMocks
    private EventServicesImpl eventServices;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddParticipant() {
        Participant participant = new Participant();
        participant.setIdPart(1);
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);

        Participant savedParticipant = eventServices.addParticipant(participant);
        assertNotNull(savedParticipant);
        assertEquals(1, savedParticipant.getIdPart());
    }

    @Test
    void testAddAffectEvenParticipant() {
        Event event = new Event();
        event.setIdEvent(1);
        event.setDescription("Annual Meeting");

        Participant participant = new Participant();
        participant.setIdPart(1);
        participant.setEvents(new HashSet<>());

        when(participantRepository.findById(1)).thenReturn(Optional.of(participant));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event updatedEvent = eventServices.addAffectEvenParticipant(event, 1);
        assertTrue(updatedEvent.getParticipants().contains(participant));
    }
}
