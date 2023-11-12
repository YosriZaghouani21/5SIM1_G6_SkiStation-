package tn.esprit.SkiStationProject.Services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.SkiStationProject.entities.Piste;
import tn.esprit.SkiStationProject.repositories.PisteRepository;
import tn.esprit.SkiStationProject.services.PisteServicesImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PisteServicesImplTest {

    @Mock
    private PisteRepository pisteRepository;

    @InjectMocks
    private PisteServicesImpl pisteService;

    @Test
    public void testRetrieveAllPistes() {
        // Arrange
        List<Piste> expectedPistes = Arrays.asList(new Piste(), new Piste());
        when(pisteRepository.findAll()).thenReturn(expectedPistes);

        // Act
        List<Piste> result = pisteService.retrieveAllPistes();

        // Assert
        assertEquals(expectedPistes.size(), result.size());
        verify(pisteRepository, times(1)).findAll();
    }

    @Test
    public void testAddPiste() {
        // Arrange
        Piste newPiste = new Piste();
        when(pisteRepository.save(any(Piste.class))).thenReturn(newPiste);

        // Act
        Piste result = pisteService.addPiste(newPiste);

        // Assert
        assertNotNull(result);
        verify(pisteRepository, times(1)).save(newPiste);
    }

    @Test
    public void testRemovePiste() {
        // Arrange
        Long numPiste = 1L;
        doNothing().when(pisteRepository).deleteById(numPiste);

        // Act
        pisteService.removePiste(numPiste);

        // Assert
        verify(pisteRepository, times(1)).deleteById(numPiste);
    }

    @Test
    public void testRetrievePiste() {
        // Arrange
        Long numPiste = 1L;
        Piste expectedPiste = new Piste();
        when(pisteRepository.findById(numPiste)).thenReturn(Optional.of(expectedPiste));

        // Act
        Piste result = pisteService.retrievePiste(numPiste);

        // Assert
        assertEquals(expectedPiste, result);
        verify(pisteRepository, times(1)).findById(numPiste);
    }
}