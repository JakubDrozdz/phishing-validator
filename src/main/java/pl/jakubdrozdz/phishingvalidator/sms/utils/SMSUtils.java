package pl.jakubdrozdz.phishingvalidator.sms.utils;

import pl.jakubdrozdz.phishingvalidator.sms.model.SMSRegistrationRequest;

public class SMSUtils {
    public static boolean isSMSRegistrationRequestValid(SMSRegistrationRequest smsRegistrationRequest) {
        return !(smsRegistrationRequest.sender() == null || smsRegistrationRequest.recipient() == null || smsRegistrationRequest.message() == null);
    }
}
