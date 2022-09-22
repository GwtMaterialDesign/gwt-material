/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2022 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.client.sanitizer;

import gwt.material.design.client.sanitizer.handler.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValueSanitizerRegistry {

    protected static Map<Class<? extends ValueSanitizeHandler>, ValueSanitizeHandler> map = new HashMap<>();

    public static void register(ValueSanitizeHandler handler) {
        map.put(handler.getClass(), handler);
    }

    public static void enable(Class<? extends ValueSanitizeHandler> handler, boolean enable) {
        map.get(handler).setEnabled(enable);
    }

    public static List<ValueSanitizeHandler> getEnabledHandlers() {
        List<ValueSanitizeHandler> handlers = new ArrayList<>();
        map.forEach((aClass, handler) -> {
            if (handler.isEnabled()) {
                handlers.add(handler);
            }
        });
        return handlers;
    }
}
