package tn.esprit.eventsproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        // MockitoAnnotations.initMocks(this); // This is redundant with @ExtendWith(MockitoExtension.class)
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







}
