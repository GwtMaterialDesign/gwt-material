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
import gwt.material.design.client.js.Window;
import gwt.material.design.jquery.client.api.Functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static gwt.material.design.client.base.MaterialWidget.window;

/**
 * @author Ben Dol
 */
public class ViewPortHandler {

    private ViewPort viewPort;

    private HandlerRegistration resize;
    private List<Boundary> boundaries;

    private Functions.Func1<ViewPortChange> then;
    private ViewPortFallback fallback;

    private boolean propagateFallback;

    public ViewPortHandler(ViewPort viewPort, Boundary boundary, Boundary... other) {
        this.viewPort = viewPort;

        boundaries = new ArrayList<>();
        boundaries.add(boundary);
        if(other != null) {
            Collections.addAll(boundaries, other);
        }
    }

    /**
     * Apply more {@link Boundary}s to the detection conditions.
     */
    public ViewPortHandler or(Boundary boundary) {
        if(!boundaries.contains(boundary)) {
            boundaries.add(boundary);
        }
        return this;
    }

    /**
     * Load the view port execution.
     *
     * @param then callback when the view port is detected.
     */
    public ViewPort then(Functions.Func1<ViewPortChange> then) {
        return then(then, fallback);
    }

    /**
     * Load the view port execution.
     *
     * @param then callback when the view port is detected.
     * @param fallback fallback when no view port detected or failure to detect the given
     *                 {@link Boundary} (using {@link #propagateFallback(boolean)})
     */
    public ViewPort then(Functions.Func1<ViewPortChange> then, ViewPortFallback fallback) {
        assert then != null : "'then' callback cannot be null";
        this.then = then;
        this.fallback = fallback;
        return load();
    }

    /**
     * Destroy the {@link ViewPortHandler}.
     */
    protected ViewPortHandler destroy() {
        unload();
        boundaries.clear();
        then = null;
        fallback = null;
        return this;
    }

    protected ViewPortHandler unload() {
        if(resize != null) {
            resize.removeHandler();
            resize = null;
        }
        return this;
    }

    /**
     * Load the windows resize handler with initial view port detection.
     */
    protected ViewPort load() {
        resize = Window.addResizeHandler(event -> {
            execute(event.getWidth(), event.getHeight());
        });

        execute(window().width(), (int)window().height());
        return viewPort;
    }

    protected void execute(int width, int height) {
        boolean match = false;
        for(Boundary boundary : boundaries) {
            if (Window.matchMedia(boundary.asMediaQuery())) {
                then.call(new ViewPortChange(width, height, boundary));
                match = true;
            } else if(propagateFallback && fallback != null && !fallback.call(new ViewPortRect(width, height))) {
                // We will not propagate.
                break;
            }
        }

        if(!propagateFallback && !match && fallback != null) {
            fallback.call(new ViewPortRect(width, height));
        }

    }

    public ViewPort getViewPort() {
        return viewPort;
    }

    /**
     * Execute fallback on each failure to detect view port.
     */
    public void propagateFallback(boolean propagateFallback) {
        this.propagateFallback = propagateFallback;
    }
}
