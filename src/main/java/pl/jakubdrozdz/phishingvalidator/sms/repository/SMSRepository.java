package pl.jakubdrozdz.phishingvalidator.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMS;

@Repository
public interface SMSRepository extends JpaRepository<SMS, Long> {
}
