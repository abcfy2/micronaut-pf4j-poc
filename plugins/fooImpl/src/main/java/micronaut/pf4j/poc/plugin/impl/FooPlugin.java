package micronaut.pf4j.poc.plugin.impl;

import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

public class FooPlugin extends Plugin {

    public FooPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        FooImpl.descriptor = getWrapper().getDescriptor();
    }

}
