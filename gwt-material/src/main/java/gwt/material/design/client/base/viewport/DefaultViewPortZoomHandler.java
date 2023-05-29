/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2023 GwtMaterialDesign
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

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.jscore.client.api.viewport.VisualViewport;

public class DefaultViewPortZoomHandler implements ViewPortZoomHandler {

    protected int initialZoomLevel;
    protected int maxZoomLevel;

    public DefaultViewPortZoomHandler(int maxZoomLevel, int initialZoomLevel) {
        this.maxZoomLevel = maxZoomLevel;
        this.initialZoomLevel = initialZoomLevel;
    }

    @Override
    public void load() {
        gwt.material.design.jscore.client.api.Window.visualViewport.addEventListener("resize", e -> {
            checkZoomLevel();
            return true;
        });

        gwt.material.design.jscore.client.api.Window.visualViewport.addEventListener("scroll", e -> {
            checkZoomLevel();
            return true;
        });
    }

    @Override
    public void unload() {

    }

    protected void checkZoomLevel() {
        VisualViewport viewport = gwt.material.design.jscore.client.api.Window.visualViewport;
        double width = viewport.width;
        double clientWidth = gwt.material.design.jscore.client.api.Window.outerWidth;
        int currentZoomLevel = (int) ((clientWidth / width) * 100);
        Element element = RootPanel.get().getElement();
        if (currentZoomLevel >= maxZoomLevel) {
            double excessZoom = currentZoomLevel - maxZoomLevel;
            double scale = ((maxZoomLevel - excessZoom) / 100.0);
            /*if (currentZoomLevel == maxZoomLevel) {
                scale = initialZoomLevel;
            }*/
            if (scale <= 0.5) {
                scale = 0.5;
            }
            element.getStyle().setProperty("zoom", scale + "");
        } else {
            element.getStyle().setProperty("zoom", "1");
        }
    }
}
