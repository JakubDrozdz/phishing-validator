package pl.jakubdrozdz.phishingvalidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.jakubdrozdz.phishingvalidator.phishing.model.PhishingDomain;

@Component
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class ApplicationRunnerBean implements ApplicationRunner {
    private final CacheManager cacheManager;
    @Override
    public void run(ApplicationArguments arg0) {
        updateCache();
    }

    @Scheduled(fixedRate = 600000, initialDelay = 300000)
    private void updateCache(){
        PhishingDomain.cachePhishingDomains().forEach(object -> cacheManager.getCache("phishingDomains").put(object, object));
        log.debug("Cache phishingDomains has been updated");
    }
}