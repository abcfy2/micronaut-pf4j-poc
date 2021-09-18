package micronaut.pf4j.poc.jobs;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import micronaut.pf4j.poc.services.PluginService;

@Singleton
public class PluginUpdateJob {

    @Inject
    PluginService pluginService;

    @Scheduled(fixedDelay = "10s")
    void executeEveryTen() {
        pluginService.updatePlugins();
    }

}
