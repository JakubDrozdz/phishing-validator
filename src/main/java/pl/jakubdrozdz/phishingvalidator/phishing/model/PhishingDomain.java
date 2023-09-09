package pl.jakubdrozdz.phishingvalidator.phishing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhishingDomain {
    public Integer registerPositionId;
    public String domainAddress;
    public String insertDate;
    public String deleteDate;

    public PhishingDomain(LinkedHashMap<String, Object> object){
        this.registerPositionId = (Integer) object.get("RegisterPositionId");
        this.domainAddress = (String) object.get("DomainAddress");
        this.insertDate = (String) object.get("InsertDate");
        this.deleteDate = (String) object.get("DeleteDate");
    }

    public static List<String> cachePhishingDomains(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> response = restTemplate.getForEntity("https://hole.cert.pl/domains/domains.json", List.class);
        List<PhishingDomain> validPhishingDomainObjects =
                response.getBody().stream()
                .filter(LinkedHashMap.class::isInstance)
                .map(object -> new PhishingDomain((LinkedHashMap<String, Object>) object))
                .filter(PhishingDomain.class::isInstance)
                .filter(object -> ((PhishingDomain) object).getDeleteDate() == null)
                .map(PhishingDomain.class::cast)
                .toList();
        return validPhishingDomainObjects.stream().map(PhishingDomain::getDomainAddress).toList();
    }
}
