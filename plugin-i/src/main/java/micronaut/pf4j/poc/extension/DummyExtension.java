package micronaut.pf4j.poc.extension;

import org.pf4j.ExtensionPoint;

import java.util.Map;

public interface DummyExtension extends ExtensionPoint {

    String response(Map<?, ?> params);

}
