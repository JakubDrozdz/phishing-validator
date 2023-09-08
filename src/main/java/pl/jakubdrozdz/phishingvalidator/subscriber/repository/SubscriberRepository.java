package pl.jakubdrozdz.phishingvalidator.subscriber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.phishingvalidator.subscriber.model.Subscriber;

import java.util.Optional;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Optional<Subscriber> findSubscriberByPhoneNumber(String phoneNumber);
}
