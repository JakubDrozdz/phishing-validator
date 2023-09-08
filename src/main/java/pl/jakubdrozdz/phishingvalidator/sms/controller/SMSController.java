package pl.jakubdrozdz.phishingvalidator.sms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.jakubdrozdz.phishingvalidator.sms.model.SMSRegistrationRequest;
import pl.jakubdrozdz.phishingvalidator.sms.service.SMSService;
import pl.jakubdrozdz.phishingvalidator.sms.utils.SMSUtils;

@Slf4j
@RestController
@RequestMapping("api/v1/sms")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class SMSController {
    private SMSService smsService;

    @PostMapping
    public String saveSMS(@RequestBody SMSRegistrationRequest smsRegistrationRequest) {
        if (!SMSUtils.isSMSRegistrationRequestValid(smsRegistrationRequest))
            throw new IllegalArgumentException("Invalid input: " + smsRegistrationRequest);
        return "";
    }
}
