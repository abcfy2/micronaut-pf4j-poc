package micronaut.pf4j.poc;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Inject;
import micronaut.pf4j.poc.services.PluginService;

public class Application implements ApplicationEventListener<ServerStartupEvent> {

    @Inject
    PluginService pluginService;

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        pluginService.loadPlugins();
        pluginService.startPlugins();
    }

}
