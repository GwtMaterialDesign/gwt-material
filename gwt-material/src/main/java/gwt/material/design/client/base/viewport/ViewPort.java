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

import java.util.ArrayList;
import java.util.List;

public class ViewPort {

    private List<ViewPortHandler> handlers = new ArrayList<>();

    public static ViewPortHandler when(Resolution resolution, Resolution... other) {
        ViewPort viewPort = new ViewPort();
        ViewPortHandler handler = new ViewPortHandler(viewPort, resolution, other);
        viewPort.handlers.add(handler);
        return handler;
    }

    public ViewPortHandler or(Resolution resolution, Resolution... other) {
        return when(resolution, other);
    }

    public ViewPort unload() {
        for(ViewPortHandler handler : handlers) {
            handler.unload();
        }
        return this;
    }

    public ViewPort load() {
        for(ViewPortHandler handler : handlers) {
            handler.load();
        }
        return this;
    }

    public ViewPort destroy() {
        for(ViewPortHandler handler : handlers) {
            handler.destroy();
        }
        return this;
    }
}
