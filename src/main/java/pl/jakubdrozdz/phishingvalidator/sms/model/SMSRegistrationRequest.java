package pl.jakubdrozdz.phishingvalidator.sms.model;

import lombok.*;

@Builder
public record SMSRegistrationRequest(String sender, String recipient, String message, int isPhishing) {
    public SMSRegistrationRequest(String sender, String recipient, String message) {
        this(sender, recipient, message, 0);
    }
}
