package pl.jakubdrozdz.phishingvalidator.subscriber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}
