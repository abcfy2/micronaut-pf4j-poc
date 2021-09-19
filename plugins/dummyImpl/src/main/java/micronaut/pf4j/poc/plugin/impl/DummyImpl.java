package micronaut.pf4j.poc.plugin.impl;

import micronaut.pf4j.poc.extension.DummyExtension;
import org.pf4j.Extension;
import org.pf4j.PluginDescriptor;

import java.util.Map;

@Extension
public class DummyImpl implements DummyExtension {

    public static PluginDescriptor descriptor;

    @Override
    public String response(Map<?, ?> params) {
        return "Response from DummyImpl, provided by plugin: '" + descriptor + "'";
    }

}
