package pl.jakubdrozdz.phishingvalidator.sms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SMS {
    @Id
    @SequenceGenerator(
            name = "sms_id_sequence",
            sequenceName = "sms_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sms_id_sequence"
    )
    private long id;
    private String sender;
    private String recipient;
    private String message;
    private Integer isPhishing; //0 - no/not agreed to check; 1 - yes
}
