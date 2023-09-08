package pl.jakubdrozdz.phishingvalidator.sms.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static pl.jakubdrozdz.phishingvalidator.constants.TestConstants.*;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMS;
import pl.jakubdrozdz.phishingvalidator.sms.service.SMSService;
import pl.jakubdrozdz.phishingvalidator.subscriber.SubscriberNotExistingException;
import pl.jakubdrozdz.phishingvalidator.subscriber.service.SubscriberService;

import java.util.Optional;

import static org.mockito.Mockito.when;

class SMSControllerTest {
    @InjectMocks
    private SMSController smsController;
    @Mock
    private SMSService smsService;
    @Mock
    private SubscriberService subscriberService;



    @BeforeEach
    void setUp(){
        smsService = Mockito.mock(SMSService.class);
        subscriberService = Mockito.mock(SubscriberService.class);
        smsController = new SMSController(smsService, subscriberService);
    }

    @Test
    void saveSMSValidSMSRegistrationRequestTest(){
        when(subscriberService.isSubscriberNumberValid(RECIPIENT)).thenReturn(Optional.of(SUBSCRIBER));
        when(smsService.save(VALID_SMS_REGISTRATION_REQUEST)).thenReturn(SMS_VALID);
        ResponseEntity<SMS> smsResponseEntity = smsController.saveSMS(VALID_SMS_REGISTRATION_REQUEST);
        HttpStatus smsResponseEntityStatusCode = HttpStatus.resolve(smsResponseEntity.getStatusCode().value());
        Assertions.assertEquals(HttpStatus.OK, smsResponseEntityStatusCode);
    }

    @Test
    void saveSMSInvalidSMSRegistrationRequestTest(){
        when(subscriberService.isSubscriberNumberValid(RECIPIENT)).thenReturn(Optional.of(SUBSCRIBER));
        Assertions.assertThrows(IllegalArgumentException.class, () -> smsController.saveSMS(INVALID_SMS_REGISTRATION_REQUEST));
    }

    @Test
    void saveSMSSubscriberNotExistingTest(){
        when(subscriberService.isSubscriberNumberValid(RECIPIENT)).thenReturn(Optional.empty());
        Assertions.assertThrows(SubscriberNotExistingException.class, () -> smsController.saveSMS(VALID_SMS_REGISTRATION_REQUEST));
    }
}
