package pl.jakubdrozdz.phishingvalidator.subscriber.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.jakubdrozdz.phishingvalidator.constants.TestConstants;
import pl.jakubdrozdz.phishingvalidator.subscriber.service.SubscriberService;

import static org.mockito.Mockito.when;

public class SubscriberControllerTest {
    @InjectMocks
    private SubscriberController subscriberController;
    @Mock
    private SubscriberService subscriberService;

    @BeforeEach
    void setUp(){
        subscriberService = Mockito.mock(SubscriberService.class);
        subscriberController = new SubscriberController(subscriberService);
    }

    @Test
    void saveSubscriberValidSubscriberRegistrationRequestTest(){
        when(subscriberService.save(TestConstants.VALID_SUBSCRIBER_REGISTRATION_REQUEST)).thenReturn(TestConstants.SUBSCRIBER);
        Assertions.assertEquals(TestConstants.SUBSCRIBER, subscriberController.saveSubscriber(TestConstants.VALID_SUBSCRIBER_REGISTRATION_REQUEST));
    }
    @Test
    void saveSubscriberInvalidSubscriberRegistrationRequestTest(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> subscriberController.saveSubscriber(TestConstants.INVALID_SUBSCRIBER_REGISTRATION_REQUEST));
    }
    @Test
    void saveSubscriberNullSubscriberRegistrationRequestTest(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> subscriberController.saveSubscriber(null));
    }
}
