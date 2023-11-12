package tn.esprit.SkiStationProject.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.SkiStationProject.entities.Subscription;
import tn.esprit.SkiStationProject.repositories.SkierRepository;
import tn.esprit.SkiStationProject.repositories.SubscriptionRepository;
import tn.esprit.SkiStationProject.entities.enums.TypeSubscription;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) 
public class SubscriptionServicesImplTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private SkierRepository skierRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionService;
    @Test
    public void testAddSubscription() {
        // Arrange
        Subscription subscription = new Subscription();
        subscription.setStartDate(LocalDate.now());
        subscription.setTypeSub(TypeSubscription.MONTHLY);
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        // Act
        Subscription result = subscriptionService.addSubscription(subscription);

        // Assert
        assertNotNull(result.getEndDate());
        assertEquals(subscription.getStartDate().plusMonths(1), result.getEndDate());
        verify(subscriptionRepository, times(1)).save(subscription);
    }

    @Test
    public void testRetrieveSubscriptionById() {
        // Arrange
        Long id = 1L;
        Subscription subscription = new Subscription();
        // Assuming BaseEntity or Subscription has a way to set ID through constructor or other means
        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(subscription));

        // Act
        Subscription result = subscriptionService.retrieveSubscriptionById(id);

        // Assert
        assertNotNull(result);
        verify(subscriptionRepository, times(1)).findById(id);
    }

    @Test
    public void testGetSubscriptionByType() {
        // Arrange
        TypeSubscription type = TypeSubscription.MONTHLY;
        Set<Subscription> subscriptions = new HashSet<>(Arrays.asList(new Subscription(), new Subscription()));
        when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(type)).thenReturn(subscriptions);

        // Act
        Set<Subscription> result = subscriptionService.getSubscriptionByType(type);

        // Assert
        assertEquals(2, result.size());
        verify(subscriptionRepository, times(1)).findByTypeSubOrderByStartDateAsc(type);
    }

    @Test
    public void testUpdateSubscription() {
        // Arrange
        Subscription subscription = new Subscription();
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        // Act
        Subscription result = subscriptionService.updateSubscription(subscription);

        // Assert
        assertEquals(subscription, result);
        verify(subscriptionRepository, times(1)).save(subscription);
    }

    @Test
    public void testRetrieveSubscriptionsByDates() {
        // Arrange
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(10);
        List<Subscription> subscriptions = Arrays.asList(new Subscription(), new Subscription());
        when(subscriptionRepository.getSubscriptionsByStartDateBetween(startDate, endDate)).thenReturn(subscriptions);

        // Act
        List<Subscription> result = subscriptionService.retrieveSubscriptionsByDates(startDate, endDate);

        // Assert
        assertEquals(2, result.size());
        verify(subscriptionRepository, times(1)).getSubscriptionsByStartDateBetween(startDate, endDate);
    }


}
