package pl.jakubdrozdz.phishingvalidator.sms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMS;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSRegistrationRequest;
import pl.jakubdrozdz.phishingvalidator.sms.service.SMSService;
import pl.jakubdrozdz.phishingvalidator.sms.utils.SMSUtils;
import pl.jakubdrozdz.phishingvalidator.subscriber.SubscriberNotExistingException;

@Slf4j
@RestController
@RequestMapping("api/v1/sms")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class SMSController {
    private final SMSService smsService;

    @PostMapping
    public ResponseEntity<SMS> saveSMS(@RequestBody SMSRegistrationRequest smsRegistrationRequest) {
        if (!SMSUtils.isSMSRegistrationRequestValid(smsRegistrationRequest))
            throw new IllegalArgumentException("Invalid input: " + smsRegistrationRequest);
        if(smsService.isSubscriberNumberValid(smsRegistrationRequest.recipient()).isEmpty()){
            throw new SubscriberNotExistingException("Subscriber with phone number " + smsRegistrationRequest.recipient() + " has not been found. Rejecting message.");
        }

        return new ResponseEntity<>(smsService.save(smsRegistrationRequest), HttpStatus.OK);
    }
}
