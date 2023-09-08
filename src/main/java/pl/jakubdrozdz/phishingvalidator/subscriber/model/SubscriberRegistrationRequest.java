package pl.jakubdrozdz.phishingvalidator.subscriber.model;

import lombok.Builder;

@Builder
public record SubscriberRegistrationRequest(String phoneNumber, int isCheckEnabled) {
    public SubscriberRegistrationRequest(String phoneNumber) {
        this(phoneNumber, 0);
    }
}
