package pl.jakubdrozdz.phishingvalidator.sms.model;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class SMSRegistrationRequest {
    private final String sender;
    private final String recipient;
    private final String message;
    private int isPhishing;

    @Override
    public String toString() {
        return "SMSRegistrationRequest{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", message='" + message + '\'' +
                ", isPhishing=" + isPhishing +
                '}';
    }
}
