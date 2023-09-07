package pl.jakubdrozdz.phishingvalidator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "Phishing Validator API", version = "1.0", description = "Phishing Validator API v1.0 documentation")
)
public class PhishingValidatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhishingValidatorApplication.class, args);
    }

}
