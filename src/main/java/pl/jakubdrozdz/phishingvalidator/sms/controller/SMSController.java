package pl.jakubdrozdz.phishingvalidator.sms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubdrozdz.phishingvalidator.phishing.controller.PhishingValidatorController;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSRegistrationRequest;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSResponseEntity;
import pl.jakubdrozdz.phishingvalidator.sms.service.SMSService;
import pl.jakubdrozdz.phishingvalidator.sms.utils.SMSUtils;
import pl.jakubdrozdz.phishingvalidator.subscriber.model.Subscriber;
import pl.jakubdrozdz.phishingvalidator.subscriber.service.SubscriberService;

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
    public ResponseEntity<SMSResponseEntity> saveSMS(@RequestBody SMSRegistrationRequest smsRegistrationRequest) {
        SMSRegistrationRequest validSMSRegistrationRequest = SMSUtils.validateSMSRegistrationRequest(smsRegistrationRequest, subscriberService);
        validSMSRegistrationRequest = smsService.detectPhishing(validSMSRegistrationRequest);
        if(validSMSRegistrationRequest.getIsPhishing() == 1){
            return new ResponseEntity<>(SMSResponseEntity.prepareSmsResponseEntity(validSMSRegistrationRequest, false), HttpStatus.OK);
        }
        SMSResponseEntity validSMS = smsService.save(validSMSRegistrationRequest);
        return new ResponseEntity<>(validSMS, HttpStatus.OK);
    }
    @PostMapping("/phsishing-validator")
    public ResponseEntity<Subscriber> setPhishingValidatorForSubscriber(@RequestBody SMSRegistrationRequest smsRegistrationRequest){
        Subscriber subscriber = phishingValidatorController.handlePhishingValidatorActivation(smsRegistrationRequest);
        smsService.save(smsRegistrationRequest);
        return new ResponseEntity<>(subscriber, HttpStatus.OK);
    }
}
