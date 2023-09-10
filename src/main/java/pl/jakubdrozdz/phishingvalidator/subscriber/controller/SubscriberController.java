package pl.jakubdrozdz.phishingvalidator.subscriber.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.jakubdrozdz.phishingvalidator.subscriber.model.Subscriber;
import pl.jakubdrozdz.phishingvalidator.subscriber.model.SubscriberRegistrationRequest;
import pl.jakubdrozdz.phishingvalidator.subscriber.service.SubscriberService;
import pl.jakubdrozdz.phishingvalidator.subscriber.utils.SubscriberUtils;

@Slf4j
@RestController
@RequestMapping("api/v1/subscribers")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class SubscriberController {
    private final SubscriberService subscriberService;

    @PostMapping
    public Subscriber saveSubscriber(@RequestBody SubscriberRegistrationRequest subscriberRegistrationRequest) {
        if (!SubscriberUtils.isSubscriberRegistrationRequestValid(subscriberRegistrationRequest))
            throw new IllegalArgumentException("Invalid input: " + subscriberRegistrationRequest);
        return subscriberService.save(subscriberRegistrationRequest);
    }
}
