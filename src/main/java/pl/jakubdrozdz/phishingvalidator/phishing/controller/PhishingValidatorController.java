package pl.jakubdrozdz.phishingvalidator.phishing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSRegistrationRequest;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSResponseEntity;
import pl.jakubdrozdz.phishingvalidator.subscriber.model.Subscriber;
import pl.jakubdrozdz.phishingvalidator.subscriber.service.SubscriberService;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PhishingValidatorController {
    private final SubscriberService subscriberService;
    private final CacheManager cacheManager;

    @Value("${phishing-validator.service.phone-number}")
    private String phishingValidatorServicePhoneNumber;

    @Value("${phishing-validator.service.start}")
    private String startMessage;

    @Value("${phishing-validator.service.stop}")
    private String stopMessage;

    public Subscriber handlePhishingValidatorActivation(SMSRegistrationRequest smsRegistrationRequest){
        Subscriber subscriber = null;
        if(smsRegistrationRequest.getRecipient().equals(phishingValidatorServicePhoneNumber)
                && (smsRegistrationRequest.getMessage().equals(startMessage) || smsRegistrationRequest.getMessage().equals(stopMessage)) ){
            subscriber = subscriberService.setPhishingValidatorEnabled(smsRegistrationRequest.getMessage().equals("START") ? true : false, smsRegistrationRequest.getSender());
        }
        return subscriber;
    }

    public SMSRegistrationRequest detectPhishing(String url, SMSRegistrationRequest smsRegistrationRequest){
        Object concurrentHashMap = cacheManager.getCache("phishingDomains").getNativeCache();
        if(concurrentHashMap instanceof ConcurrentHashMap<?,?> &&
                !((ConcurrentHashMap<?,?>) concurrentHashMap).values().stream().filter(String.class::isInstance).map(String.class::cast).filter(url::contains).toList().isEmpty()){
            smsRegistrationRequest.setIsPhishing(1);
        }
        return smsRegistrationRequest;
    }
}
