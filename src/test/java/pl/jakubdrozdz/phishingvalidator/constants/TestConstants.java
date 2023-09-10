package pl.jakubdrozdz.phishingvalidator.constants;

import pl.jakubdrozdz.phishingvalidator.sms.model.SMS;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSRegistrationRequest;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSResponseEntity;
import pl.jakubdrozdz.phishingvalidator.subscriber.model.Subscriber;
import pl.jakubdrozdz.phishingvalidator.subscriber.model.SubscriberRegistrationRequest;

public class TestConstants {
    public static final String INVALID_PHONE_NUMBER = "000000000";
    public static final String SENDER = "123456789";
    public static final String RECIPIENT = "987654321";
    public static final String MESSAGE = "This is test execution! Ignore!";
    public static final String PHISHING_MESSAGE = "Test from http://www.mbank.pl.online.pll/log-in";
    public static final Subscriber SUBSCRIBER = Subscriber.builder().phoneNumber(RECIPIENT).isCheckEnabled(0).build();
    public static final SMSRegistrationRequest VALID_SMS_REGISTRATION_REQUEST = SMSRegistrationRequest.builder()
            .sender(TestConstants.SENDER)
            .recipient(TestConstants.RECIPIENT)
            .message(TestConstants.MESSAGE)
            .isPhishing(0)
            .build();
    public static final SMSRegistrationRequest INVALID_SMS_REGISTRATION_REQUEST = SMSRegistrationRequest.builder()
            .sender(null)
            .recipient(TestConstants.RECIPIENT)
            .message(TestConstants.MESSAGE)
            .isPhishing(0)
            .build();
    public static final SMS SMS_VALID = SMS.builder()
            .sender(SENDER)
            .recipient(RECIPIENT)
            .message(MESSAGE)
            .isPhishing(0)
            .build();
    public static final SubscriberRegistrationRequest VALID_SUBSCRIBER_REGISTRATION_REQUEST = SubscriberRegistrationRequest.builder()
            .phoneNumber(RECIPIENT)
            .isCheckEnabled(0)
            .build();
    public static final SubscriberRegistrationRequest INVALID_SUBSCRIBER_REGISTRATION_REQUEST = SubscriberRegistrationRequest.builder()
            .phoneNumber(null)
            .isCheckEnabled(0)
            .build();
    public static final SMSResponseEntity SMS_RESPONSE_VALID = SMSResponseEntity.builder()
            .sender(SENDER)
            .recipient(RECIPIENT)
            .message(MESSAGE)
            .isPhishing(0)
            .isPersisted(true)
            .build();
    public static final SMSResponseEntity SMS_RESPONSE_INVALID = SMSResponseEntity.builder()
            .sender(SENDER)
            .recipient(RECIPIENT)
            .message(PHISHING_MESSAGE)
            .isPhishing(0)
            .isPersisted(false)
            .build();

    public static final SMSRegistrationRequest VALID_SMS_REGISTRATION_REQUEST_PHISHING = SMSRegistrationRequest.builder()
            .sender(SENDER)
            .recipient(RECIPIENT)
            .message(PHISHING_MESSAGE)
            .isPhishing(0)
            .build();
    public static final SMSRegistrationRequest VALID_SMS_REGISTRATION_REQUEST_PHISHING_WITH_STATUS = SMSRegistrationRequest.builder()
            .sender(SENDER)
            .recipient(RECIPIENT)
            .message(PHISHING_MESSAGE)
            .isPhishing(1)
            .build();
}
