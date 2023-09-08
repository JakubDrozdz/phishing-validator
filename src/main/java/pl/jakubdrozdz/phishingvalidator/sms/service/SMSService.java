package pl.jakubdrozdz.phishingvalidator.sms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubdrozdz.phishingvalidator.sms.repository.SMSRepository;

@Service
@RequiredArgsConstructor
public class SMSService {
    private final SMSRepository smsRepository;
}
