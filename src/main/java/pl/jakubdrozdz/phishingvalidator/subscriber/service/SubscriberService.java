package pl.jakubdrozdz.phishingvalidator.subscriber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubdrozdz.phishingvalidator.subscriber.repository.SubscriberRepository;

@Service
@RequiredArgsConstructor
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;
}
