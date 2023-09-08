package pl.jakubdrozdz.phishingvalidator.subscriber.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.jakubdrozdz.phishingvalidator.constants.TestConstants;
import pl.jakubdrozdz.phishingvalidator.subscriber.model.Subscriber;
import pl.jakubdrozdz.phishingvalidator.subscriber.repository.SubscriberRepository;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class SubscriberServiceTest {
    @InjectMocks
    private SubscriberService subscriberService;
    @Mock
    private SubscriberRepository subscriberRepository;

    @BeforeEach
    void setUp(){
        subscriberRepository = Mockito.mock(SubscriberRepository.class);
        subscriberService = new SubscriberService(subscriberRepository);
    }

    @Test
    void isSubscriberNumberValidTest(){
        when(subscriberRepository.findSubscriberByPhoneNumber(TestConstants.RECIPIENT)).thenReturn(Optional.of(TestConstants.SUBSCRIBER));
        Optional<Subscriber> subscriber = subscriberService.isSubscriberNumberValid(TestConstants.RECIPIENT);
        Assertions.assertEquals(TestConstants.SUBSCRIBER, subscriber.get());
    }
    @Test
    void isSubscriberNumberInvalidTest(){
        when(subscriberRepository.findSubscriberByPhoneNumber(TestConstants.INVALID_PHONE_NUMBER)).thenReturn(Optional.empty());
        Optional<Subscriber> subscriber = subscriberService.isSubscriberNumberValid(TestConstants.INVALID_PHONE_NUMBER);
        Assertions.assertTrue(subscriber.isEmpty());
    }

    @Test
    void saveValidSubscriberRegistrationRequestTest(){
        when(subscriberRepository.save(TestConstants.SUBSCRIBER)).thenReturn(TestConstants.SUBSCRIBER);
        Assertions.assertEquals(TestConstants.SUBSCRIBER, subscriberService.save(TestConstants.VALID_SUBSCRIBER_REGISTRATION_REQUEST));
    }

}
