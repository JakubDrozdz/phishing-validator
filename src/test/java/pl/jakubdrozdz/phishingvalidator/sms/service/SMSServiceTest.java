package pl.jakubdrozdz.phishingvalidator.sms.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import pl.jakubdrozdz.phishingvalidator.constants.TestConstants;
import pl.jakubdrozdz.phishingvalidator.phishing.controller.PhishingValidatorController;
import pl.jakubdrozdz.phishingvalidator.sms.repository.SMSRepository;
import pl.jakubdrozdz.phishingvalidator.subscriber.service.SubscriberService;

class SMSServiceTest {
    @InjectMocks
    private SMSService smsService;

    @Mock
    private SMSRepository smsRepository;
    @Mock
    private PhishingValidatorController phishingValidatorController;
    @Mock
    private SubscriberService subscriberService;

    @BeforeEach
    void setUp(){
        smsRepository = Mockito.mock(SMSRepository.class);
        phishingValidatorController = Mockito.mock(PhishingValidatorController.class);
        subscriberService = Mockito.mock(SubscriberService.class);
        smsService = new SMSService(smsRepository, phishingValidatorController, subscriberService);
    }

    @Test
    void saveValidSMSRegistrationRequestTest(){
        when(smsRepository.save(TestConstants.SMS_VALID)).thenReturn(TestConstants.SMS_VALID);
        Assertions.assertEquals(TestConstants.SMS_RESPONSE_VALID, smsService.save(TestConstants.VALID_SMS_REGISTRATION_REQUEST));
    }
}
