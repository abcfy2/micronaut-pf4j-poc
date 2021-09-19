package micronaut.pf4j.poc.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import micronaut.pf4j.poc.extension.DummyExtension;
import micronaut.pf4j.poc.extension.FooExtension;
import micronaut.pf4j.poc.services.PluginService;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;

@Controller()
public class RootController {

    @Inject
    PluginService pluginService;

    @Get(produces = MediaType.APPLICATION_JSON)
    public Map<?, ?> index(@NotNull String plugin) {
        Map<?, ?> response = Map.of();
        switch (plugin) {
            case "plugin1":
                DummyExtension dummyPlugin = pluginService.getExtension(DummyExtension.class);
                if (Objects.nonNull(dummyPlugin)) {
                    String pluginResponse = dummyPlugin.response(Map.of());
                    return Map.of(
                            "plugin", "DummyPlugin"
                            , "response", pluginResponse
                    );
                }
                break;
            case "plugin2":
                FooExtension fooPlugin = pluginService.getExtension(FooExtension.class);
                if (Objects.nonNull(fooPlugin)) {
                    String pluginResponse = fooPlugin.response(Map.of());
                    return Map.of(
                            "plugin", "FooPlugin"
                            , "response", pluginResponse
                    );
                }
                break;
            default:
                response = Map.of("error", "unsupported plugin parameter '" + plugin + "'");
        }

        return response;
    }

}
