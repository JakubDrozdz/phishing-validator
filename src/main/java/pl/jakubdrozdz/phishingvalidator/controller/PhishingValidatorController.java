package pl.jakubdrozdz.phishingvalidator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSRegistrationRequest;
import pl.jakubdrozdz.phishingvalidator.subscriber.model.Subscriber;
import pl.jakubdrozdz.phishingvalidator.subscriber.service.SubscriberService;

@RequiredArgsConstructor
@Component
public class PhishingValidatorController {
    private final SubscriberService subscriberService;

    @Value("${phishing-validator.service.phone-number}")
    private String phishingValidatorServicePhoneNumber;

    @Value("${phishing-validator.service.start}")
    private String startMessage;

    @Value("${phishing-validator.service.stop}")
    private String stopMessage;

    public Subscriber handlePhishingValidatorActivation(SMSRegistrationRequest smsRegistrationRequest){
        Subscriber subscriber = null;
        if(smsRegistrationRequest.recipient().equals(phishingValidatorServicePhoneNumber)
                && (smsRegistrationRequest.message().equals(startMessage) || smsRegistrationRequest.message().equals(stopMessage)) ){
            subscriber = subscriberService.setPhishingValidatorEnabled(smsRegistrationRequest.message().equals("START") ? true : false, smsRegistrationRequest.sender());
        }
        return subscriber;
    }
}
