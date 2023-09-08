package pl.jakubdrozdz.phishingvalidator.sms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMS;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSRegistrationRequest;
import pl.jakubdrozdz.phishingvalidator.sms.repository.SMSRepository;
import pl.jakubdrozdz.phishingvalidator.subscriber.model.Subscriber;
import pl.jakubdrozdz.phishingvalidator.subscriber.repository.SubscriberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SMSService {
    private final SMSRepository smsRepository;
    private final SubscriberRepository subscriberRepository;

    public Optional<Subscriber> isSubscriberNumberValid(String phoneNumber){
        return subscriberRepository.findSubscriberByPhoneNumber(phoneNumber);
    }

    public SMS save(SMSRegistrationRequest smsRegistrationRequest){
        return smsRepository.save(
                SMS.builder()
                        .sender(smsRegistrationRequest.sender())
                        .recipient(smsRegistrationRequest.recipient())
                        .message(smsRegistrationRequest.message())
                        //TODO: implement phishing check
                        .isPhishing(0)
                        .build()
        );
    }
}
