package tn.esprit.eventsproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceMockitoTest {

    @InjectMocks
    private EventServicesImpl eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Add participant and expect save")
    void testAddParticipant() {
        Participant participant = new Participant();
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);

        Participant savedParticipant = eventService.addParticipant(participant);

        assertNotNull(savedParticipant);
        verify(participantRepository).save(participant);
    }

    @Test
    @DisplayName("Affect participant to event and expect event update")
    void testAffectParticipantToEvent() {
        Participant participant = new Participant();
        participant.setIdPart(1);
        Event event = new Event();
        when(participantRepository.findById(1)).thenReturn(Optional.of(participant));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event updatedEvent = eventService.addAffectEvenParticipant(event, 1);

        assertNotNull(updatedEvent);
        verify(eventRepository).save(event);
        assertTrue(updatedEvent.getParticipants().contains(participant));
    }

    @Test
    @DisplayName("Assign logistics to an event by event description")
    void testAssignLogisticsToEventByDescription() {
        Event event = new Event();
        event.setDescription("Annual Meet");
        Logistics logistics = new Logistics();
        when(eventRepository.findByDescription("Annual Meet")).thenReturn(event);
        when(logisticsRepository.save(any(Logistics.class))).thenReturn(logistics);

        Logistics assignedLogistics = eventService.addAffectLog(logistics, "Annual Meet");

        assertNotNull(assignedLogistics);
        verify(eventRepository).save(event);
        verify(logisticsRepository).save(logistics);
    }

    @Test
    @DisplayName("Get logistics by date range")
    void testGetLogisticsByDateRange() {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusDays(5);
        List<Event> events = Arrays.asList(new Event(), new Event());
        when(eventRepository.findByDateDebutBetween(start, end)).thenReturn(events);

        List<Logistics> logisticsList = eventService.getLogisticsDates(start, end);

        assertNotNull(logisticsList);
    }

    @Test
    @DisplayName("Calculate cost for events")
    void testCalculateEventCost() {
        List<Event> events = Arrays.asList(new Event());
        when(eventRepository.findAll()).thenReturn(events);

        assertDoesNotThrow(() -> eventService.calculCout());
    }
}
