package pl.jakubdrozdz.phishingvalidator.subscriber.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.jakubdrozdz.phishingvalidator.constants.TestConstants;

public class SubscriberUtilsTest {
    @Test
    void isSubscriberRegistrationRequestValidTest(){
        Assertions.assertTrue(SubscriberUtils.isSubscriberRegistrationRequestValid(TestConstants.VALID_SUBSCRIBER_REGISTRATION_REQUEST));
    }
    @Test
    void isSubscriberRegistrationRequestInvalidTest(){
        Assertions.assertFalse(SubscriberUtils.isSubscriberRegistrationRequestValid(TestConstants.INVALID_SUBSCRIBER_REGISTRATION_REQUEST));
    }
    @Test
    void isSubscriberRegistrationRequestNullTest(){
        Assertions.assertFalse(SubscriberUtils.isSubscriberRegistrationRequestValid(null));
    }
}
