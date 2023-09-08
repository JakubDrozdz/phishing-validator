package pl.jakubdrozdz.phishingvalidator.subscriber.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberSMS {
    @Id
    @SequenceGenerator(
            name = "subscriber_sms_id_sequence",
            sequenceName = "subscriber_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subscriber_sms_id_sequence"
    )
    private long id;
    @NotNull
    private long subscriberId;
    @NotNull
    private long smsId;
}
