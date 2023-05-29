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
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.jscore.client.api.viewport.VisualViewport;

public class DefaultViewPortZoomHandler implements ViewPortZoomHandler {

    protected int zoomBoundaryLevel;

    public DefaultViewPortZoomHandler(int zoomBoundaryLevel) {
        this.zoomBoundaryLevel = zoomBoundaryLevel;
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
        if (currentZoomLevel >= zoomBoundaryLevel) {
            double excessZoom = currentZoomLevel - zoomBoundaryLevel; // 25
            double scale = ((zoomBoundaryLevel - excessZoom) / 100.0);
            MaterialToast.fireToast("Current Zoom Level : " + currentZoomLevel);
            MaterialToast.fireToast("Zoom Level Boundary : " + zoomBoundaryLevel);

            // Standardized the page scale or zoom level.
            /**
             *     transform: scale(0.5);
             *     transform-origin: 0 0;
             *     width: calc(100% + 100%);
             */

            element.getStyle().setProperty("transform", "scale(" + 0.5 + ")");
            element.getStyle().setProperty("transformOrigin", "0 0");
            element.getStyle().setProperty("width", "200%");
        } else {
            element.getStyle().setProperty("transform", "scale(" + 1 + ")");
            element.getStyle().setProperty("width", "100%");
        }
    }
}
