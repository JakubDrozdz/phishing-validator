package pl.jakubdrozdz.phishingvalidator.sms.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.jakubdrozdz.phishingvalidator.constants.TestConstants;

public class SMSUtilsTest {
    @Test
    void isSMSRegistrationRequestValidSuccessTest(){
        Assertions.assertTrue(SMSUtils.isSMSRegistrationRequestValid(TestConstants.VALID_SMS_REGISTRATION_REQUEST));
    }
    @Test
    void isSMSRegistrationRequestValidFailTest(){
        Assertions.assertFalse(SMSUtils.isSMSRegistrationRequestValid(TestConstants.INVALID_SMS_REGISTRATION_REQUEST));
    }

    @Test
    void isSMSRegistrationRequestValidNullTest(){
        Assertions.assertFalse(SMSUtils.isSMSRegistrationRequestValid(null));
    }
}
