package micronaut.pf4j.poc.plugin.impl;

import micronaut.pf4j.poc.extension.FooExtension;
import org.pf4j.Extension;
import org.pf4j.PluginDescriptor;

import java.util.Map;

@Extension
public class FooImpl implements FooExtension {

    public static PluginDescriptor descriptor;

    @Override
    public String response(Map<?, ?> params) {
        return "Response from FooImpl, provided by plugin: '" + descriptor + "'";
    }

}
