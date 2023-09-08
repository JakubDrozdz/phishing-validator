package pl.jakubdrozdz.phishingvalidator.sms.utils;

import lombok.extern.slf4j.Slf4j;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSRegistrationRequest;

@Slf4j
public class SMSUtils {
    public static boolean isSMSRegistrationRequestValid(SMSRegistrationRequest smsRegistrationRequest) {
        if(smsRegistrationRequest == null){
            log.error("SMSRegistrationRequest is null. Aborting execution");
            return false;
        }
        return !(smsRegistrationRequest.sender() == null || smsRegistrationRequest.recipient() == null || smsRegistrationRequest.message() == null);
    }
}
