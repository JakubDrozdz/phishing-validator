package pl.jakubdrozdz.phishingvalidator.subscriber.model;

import jakarta.persistence.*;
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

    private long subscriberId;

    private long smsId;
}
