package pl.jakubdrozdz.phishingvalidator.sms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubdrozdz.phishingvalidator.controller.PhishingValidatorController;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMS;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSRegistrationRequest;
import pl.jakubdrozdz.phishingvalidator.sms.service.SMSService;
import pl.jakubdrozdz.phishingvalidator.sms.utils.SMSUtils;
import pl.jakubdrozdz.phishingvalidator.subscriber.SubscriberNotExistingException;
import pl.jakubdrozdz.phishingvalidator.subscriber.model.Subscriber;
import pl.jakubdrozdz.phishingvalidator.subscriber.service.SubscriberService;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/sms")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class SMSController {
    private final SMSService smsService;

    private final SubscriberService subscriberService;

    private final PhishingValidatorController phishingValidatorController;

    @PostMapping
    public ResponseEntity<SMS> saveSMS(@RequestBody SMSRegistrationRequest smsRegistrationRequest) {
        SMS validSMS = SMSUtils.validateSMS(smsRegistrationRequest, subscriberService, smsService);
        return new ResponseEntity<>(validSMS, HttpStatus.OK);
    }
    @PostMapping("/phsishing-validator")
    public ResponseEntity<Subscriber> setPhishingValidatorForSubscriber(@RequestBody SMSRegistrationRequest smsRegistrationRequest){
        SMSUtils.validateSMS(smsRegistrationRequest, subscriberService, smsService);
        Subscriber subscriber = phishingValidatorController.handlePhishingValidatorActivation(smsRegistrationRequest);
        return new ResponseEntity<>(subscriber, HttpStatus.OK);
    }
}
