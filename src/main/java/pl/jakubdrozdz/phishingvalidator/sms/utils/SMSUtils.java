package pl.jakubdrozdz.phishingvalidator.sms.utils;

import pl.jakubdrozdz.phishingvalidator.sms.model.SMSRegistrationRequest;

public class SMSUtils {
    public static boolean isSMSRegistrationRequestValid(SMSRegistrationRequest smsRegistrationRequest) {
        return smsRegistrationRequest.getSender() != null || smsRegistrationRequest.getRecipient() != null || smsRegistrationRequest.getMessage() != null;
    }
}
