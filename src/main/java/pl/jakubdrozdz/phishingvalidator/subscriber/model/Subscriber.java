package pl.jakubdrozdz.phishingvalidator.subscriber.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Subscriber {
    @Id
    @SequenceGenerator(
            name = "subscriber_id_sequence",
            sequenceName = "subscriber_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subscriber_id_sequence"
    )
    private long id;
    @NotNull
    @Column(unique = true)
    private String phoneNumber;
    @NotNull
    private int isCheckEnabled;

    @OneToMany
    @JoinColumn(name = "subscriberId")
    private List<SubscriberSMS> subscriberMessages = new ArrayList<>();
}
