package micronaut.pf4j.poc.plugin.impl;

import micronaut.pf4j.poc.extension.DummyExtension;
import org.pf4j.Extension;

import java.util.Map;

@Extension
public class DummyImpl implements DummyExtension {

    @Override
    public String response(Map<?, ?> params) {
        return "Response from DummyImpl";
    }

}
