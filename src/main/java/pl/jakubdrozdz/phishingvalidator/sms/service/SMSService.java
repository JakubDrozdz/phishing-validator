package pl.jakubdrozdz.phishingvalidator.sms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubdrozdz.phishingvalidator.phishing.controller.PhishingValidatorController;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMS;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSRegistrationRequest;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSResponseEntity;
import pl.jakubdrozdz.phishingvalidator.sms.repository.SMSRepository;
import pl.jakubdrozdz.phishingvalidator.sms.utils.SMSUtils;

@Service
@RequiredArgsConstructor
public class SMSService {
    private final SMSRepository smsRepository;
    private final PhishingValidatorController phishingValidatorController;

    public SMSResponseEntity save(SMSRegistrationRequest smsRegistrationRequest){
        smsRepository.save(
                SMS.builder()
                        .sender(smsRegistrationRequest.getSender())
                        .recipient(smsRegistrationRequest.getRecipient())
                        .message(smsRegistrationRequest.getMessage())
                        .isPhishing(smsRegistrationRequest.getIsPhishing())
                        .build()
        );
        return SMSResponseEntity.prepareSmsResponseEntity(smsRegistrationRequest, true);
    }

    public SMSRegistrationRequest detectPhishing(SMSRegistrationRequest smsRegistrationRequest){
        String url = SMSUtils.extracUrlFromSMS(smsRegistrationRequest);
        if(url == null){
            if(smsRegistrationRequest.getIsPhishing() != 0){
                smsRegistrationRequest.setIsPhishing(0);
                return smsRegistrationRequest;
            }
            return smsRegistrationRequest;
        }
        return phishingValidatorController.detectPhishing(url, smsRegistrationRequest);
    }
}
