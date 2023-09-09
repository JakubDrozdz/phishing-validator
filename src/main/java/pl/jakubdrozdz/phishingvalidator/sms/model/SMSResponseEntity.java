package pl.jakubdrozdz.phishingvalidator.sms.model;

import lombok.Builder;

@Builder
public record SMSResponseEntity(String sender, String recipient, String message, int isPhishing, boolean isPersisted) {
    public static SMSResponseEntity prepareSmsResponseEntity(SMSRegistrationRequest smsRegistrationRequest, boolean isPersisted){
        return SMSResponseEntity.builder()
                .sender(smsRegistrationRequest.getSender())
                .recipient(smsRegistrationRequest.getRecipient())
                .message(smsRegistrationRequest.getMessage())
                .isPhishing(smsRegistrationRequest.getIsPhishing())
                .isPersisted(isPersisted)
                .build();
    }
}
