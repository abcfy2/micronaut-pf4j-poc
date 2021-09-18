package micronaut.pf4j.poc.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import micronaut.pf4j.poc.extension.DummyExtension;
import micronaut.pf4j.poc.extension.FooExtension;
import micronaut.pf4j.poc.services.PluginService;

import java.util.Map;
import java.util.Objects;

@Controller()
public class RootController {

    @Inject
    PluginService pluginService;

    @Get(produces = MediaType.APPLICATION_JSON)
    public Map<?, ?> index() {
        return Map.of("key1", "value");
    }

    @Get(uri = "/plugin1", produces = MediaType.APPLICATION_JSON)
    public Map<?, ?> plugin1() {
        DummyExtension dummyPlugin = pluginService.getExtension(DummyExtension.class);
        if (Objects.nonNull(dummyPlugin)) {
            String response = dummyPlugin.response(Map.of());
            return Map.of(
                    "plugin", "DummyPlugin"
                    , "response", response
            );
        }
        return Map.of();
    }

    @Get(uri = "/plugin2", produces = MediaType.APPLICATION_JSON)
    public Map<?, ?> plugin2() {
        FooExtension fooPlugin = pluginService.getExtension(FooExtension.class);
        if (Objects.nonNull(fooPlugin)) {
            String response = fooPlugin.response(Map.of());
            return Map.of(
                    "plugin", "FooPlugin"
                    , "response", response
            );
        }
        return Map.of();
    }

}
