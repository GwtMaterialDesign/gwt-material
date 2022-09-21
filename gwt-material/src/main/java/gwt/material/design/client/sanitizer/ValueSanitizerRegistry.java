package gwt.material.design.client.sanitizer;

import gwt.material.design.client.events.HandlerRegistry;
import gwt.material.design.client.sanitizer.handler.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValueSanitizerRegistry {

    protected static Map<Class<? extends ValueSanitizerHandler>, ValueSanitizerHandler> map = new HashMap<>();

    public static void register(ValueSanitizerHandler handler) {
        map.put(handler.getClass(), handler);
    }

    public static void enable(Class<? extends ValueSanitizerHandler> handler, boolean enable) {
        map.get(handler).setEnabled(enable);
    }

    public static List<ValueSanitizerHandler> getEnabledHandlers() {
        List<ValueSanitizerHandler> handlers = new ArrayList<>();
        map.forEach((aClass, handler) -> {
            if (handler.isEnabled()) {
                handlers.add(handler);
            }
        });
        return handlers;
    }
}
