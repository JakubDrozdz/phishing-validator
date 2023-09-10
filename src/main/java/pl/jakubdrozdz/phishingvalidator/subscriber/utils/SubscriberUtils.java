package pl.jakubdrozdz.phishingvalidator.subscriber.utils;

import pl.jakubdrozdz.phishingvalidator.subscriber.model.SubscriberRegistrationRequest;

public class SubscriberUtils {
    public static boolean isSubscriberRegistrationRequestValid(SubscriberRegistrationRequest subscriberRegistrationRequest) {
        return subscriberRegistrationRequest != null && subscriberRegistrationRequest.phoneNumber() != null;
    }
}
