package micronaut.pf4j.poc.plugin.impl;

import micronaut.pf4j.poc.extension.FooExtension;
import org.pf4j.Extension;

import java.util.Map;

@Extension
public class FooImpl implements FooExtension {

    @Override
    public String response(Map<?, ?> params) {
        return "Response from FooImpl";
    }

}
