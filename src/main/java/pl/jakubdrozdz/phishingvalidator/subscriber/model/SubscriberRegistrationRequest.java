package pl.jakubdrozdz.phishingvalidator.subscriber.model;

public record SubscriberRegistrationRequest(String phoneNumber, int isCheckEnabled) {
    public SubscriberRegistrationRequest(String phoneNumber) {
        this(phoneNumber, 0);
    }
}
