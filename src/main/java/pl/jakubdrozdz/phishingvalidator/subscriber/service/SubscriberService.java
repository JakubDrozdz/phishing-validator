package pl.jakubdrozdz.phishingvalidator.subscriber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubdrozdz.phishingvalidator.subscriber.SubscriberNotExistingException;
import pl.jakubdrozdz.phishingvalidator.subscriber.model.Subscriber;
import pl.jakubdrozdz.phishingvalidator.subscriber.model.SubscriberRegistrationRequest;
import pl.jakubdrozdz.phishingvalidator.subscriber.repository.SubscriberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;

    public Subscriber save(SubscriberRegistrationRequest subscriberRegistrationRequest){
        return subscriberRepository.save(
                Subscriber.builder()
                        .phoneNumber(subscriberRegistrationRequest.phoneNumber())
                        .isCheckEnabled(subscriberRegistrationRequest.isCheckEnabled())
                        .build()
        );
    }

    public Optional<Subscriber> isSubscriberNumberValid(String phoneNumber){
        return subscriberRepository.findSubscriberByPhoneNumber(phoneNumber);
    }

    public Subscriber setPhishingValidatorEnabled(boolean shouldEnable, String phoneNumber){
        Optional<Subscriber> subscriber = isSubscriberNumberValid(phoneNumber);
        if(subscriber.isEmpty()){
            throw new SubscriberNotExistingException("Subscriber with phone number " + phoneNumber + " has not been found. Rejecting message.");
        }
        subscriber.get().setIsCheckEnabled(shouldEnable ? 1 : 0);
        return subscriberRepository.save(subscriber.get());
    }
}
