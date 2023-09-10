package pl.jakubdrozdz.phishingvalidator.sms.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SMSRegistrationRequest{
    private String sender;
    private String recipient;
    private String message;
    private int isPhishing;
    public SMSRegistrationRequest(String sender, String recipient, String message) {
        this(sender, recipient, message, 0);
    }
}
