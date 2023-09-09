package pl.jakubdrozdz.phishingvalidator.sms.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSRegistrationRequest;
import pl.jakubdrozdz.phishingvalidator.sms.service.SMSService;
import pl.jakubdrozdz.phishingvalidator.subscriber.SubscriberNotExistingException;
import pl.jakubdrozdz.phishingvalidator.subscriber.service.SubscriberService;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
public class SMSUtils {
    private final SMSService smsService;
    public static boolean isSMSRegistrationRequestValid(SMSRegistrationRequest smsRegistrationRequest) {
        if(smsRegistrationRequest == null){
            log.error("SMSRegistrationRequest is null. Aborting execution");
            return false;
        }
        return !(smsRegistrationRequest.getSender() == null || smsRegistrationRequest.getRecipient() == null || smsRegistrationRequest.getMessage() == null);
    }

    public static SMSRegistrationRequest validateSMSRegistrationRequest(SMSRegistrationRequest smsRegistrationRequest, SubscriberService subscriberService){
        if (!SMSUtils.isSMSRegistrationRequestValid(smsRegistrationRequest))
            throw new IllegalArgumentException("Invalid input: " + smsRegistrationRequest);
        if(subscriberService.isSubscriberNumberValid(smsRegistrationRequest.getRecipient()).isEmpty()){
            throw new SubscriberNotExistingException("Subscriber with phone number " + smsRegistrationRequest.getRecipient() + " has not been found. Rejecting message.");
        }
        return smsRegistrationRequest;
    }

    public static String extracUrlFromSMS(SMSRegistrationRequest smsRegistrationRequest){
        String message = smsRegistrationRequest.getMessage();
        Pattern urlPattern = Pattern.compile("https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$");
        Matcher urlMatcher = urlPattern.matcher(message);

        return (urlMatcher.find() && isUrlValid(urlMatcher.group())) ? urlMatcher.group() : null;
    }

    private static boolean isUrlValid(String url){
        try {
            new URL(url).toURI();
            return true;
        } catch (URISyntaxException | MalformedURLException e) {
            return false;
        }
    }
}
