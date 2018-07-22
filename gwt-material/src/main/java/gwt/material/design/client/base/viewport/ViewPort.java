/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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
package gwt.material.design.client.base.viewport;

import com.google.gwt.event.shared.HandlerRegistration;

import java.util.ArrayList;
import java.util.List;

/**
 * View port detection utility.
 *
 * @author Ben
 */
public class ViewPort implements HandlerRegistration {

    private List<ViewPortHandler> handlers = new ArrayList<>();

    /**
     * When {@link ViewPort} resolution is detected.
     */
    public static ViewPortHandler when(Boundary boundary, Boundary... other) {
        ViewPort viewPort = new ViewPort();
        ViewPortHandler handler = new ViewPortHandler(viewPort, boundary, other);
        viewPort.handlers.add(handler);
        return handler;
    }

    /**
     * Or when this {@link ViewPort} resolution is detected.
     */
    public ViewPortHandler or(Boundary resolution, Boundary... other) {
        return when(resolution, other);
    }

    /**
     * Unload the view port detection.
     */
    public ViewPort unload() {
        for(ViewPortHandler handler : handlers) {
            handler.unload();
        }
        return this;
    }

    /**
     * Load the view port detection.
     */
    public ViewPort load() {
        for(ViewPortHandler handler : handlers) {
            handler.load();
        }
        return this;
    }

    /**
     * Destroy the view port detection (use {@link #unload()} to temporarily
     * disable then {@link #load()} to re-enable.
     */
    public ViewPort destroy() {
        for(ViewPortHandler handler : handlers) {
            handler.destroy();
        }
        return this;
    }

    @Override
    public void removeHandler() {
        unload();
    }
}
