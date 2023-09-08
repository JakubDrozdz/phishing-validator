package pl.jakubdrozdz.phishingvalidator.sms.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMS;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSRegistrationRequest;
import pl.jakubdrozdz.phishingvalidator.sms.service.SMSService;
import pl.jakubdrozdz.phishingvalidator.subscriber.SubscriberNotExistingException;
import pl.jakubdrozdz.phishingvalidator.subscriber.model.Subscriber;
import pl.jakubdrozdz.phishingvalidator.subscriber.service.SubscriberService;

@Slf4j
@RequiredArgsConstructor
public class SMSUtils {
    private final SMSService smsService;
    public static boolean isSMSRegistrationRequestValid(SMSRegistrationRequest smsRegistrationRequest) {
        if(smsRegistrationRequest == null){
            log.error("SMSRegistrationRequest is null. Aborting execution");
            return false;
        }
        return !(smsRegistrationRequest.sender() == null || smsRegistrationRequest.recipient() == null || smsRegistrationRequest.message() == null);
    }

    public static SMS validateSMS(SMSRegistrationRequest smsRegistrationRequest, SubscriberService subscriberService, SMSService smsService){
        if (!SMSUtils.isSMSRegistrationRequestValid(smsRegistrationRequest))
            throw new IllegalArgumentException("Invalid input: " + smsRegistrationRequest);
        if(subscriberService.isSubscriberNumberValid(smsRegistrationRequest.recipient()).isEmpty()){
            throw new SubscriberNotExistingException("Subscriber with phone number " + smsRegistrationRequest.recipient() + " has not been found. Rejecting message.");
        }
        return smsService.save(smsRegistrationRequest);
    }
}
