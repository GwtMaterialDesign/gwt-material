/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2019 GwtMaterialDesign
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
package gwt.material.design.client.adaptive;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.viewport.Resolution;
import gwt.material.design.client.base.viewport.ViewPort;
import gwt.material.design.client.base.viewport.WidthBoundary;
import gwt.material.design.client.js.Window;

//TODO: More usecase implementation
public class AdaptiveWidget<T extends Widget> extends MaterialWidget {

    private T widget;
    protected AdaptiveWidgetMap<T> widgetMap;

    public AdaptiveWidget() {
        super(Document.get().createDivElement());
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        load();
    }

    public void load() {
        ViewPort.when(Resolution.ALL_DEVICES).then(viewPortChange -> loadViewPort());
    }

    protected void loadViewPort() {
        for (WidthBoundary widthBoundary : getWidgetMap().keySet()) {
            if (Window.matchMedia(widthBoundary.asMediaQuery())) {
                T currentWidget = getWidgetMap().get(widthBoundary);
                setWidget(currentWidget);
            }
        }
    }

    public AdaptiveWidget register(Resolution resolution, T widget) {
        if (widgetMap == null) {
            widgetMap = new AdaptiveWidgetMap<>();
        }

        widgetMap.put(resolution, widget);
        return this;
    }

    public AdaptiveWidget unregister(Resolution resolution) {
        if (widgetMap != null) {
            widgetMap.remove(resolution);
        }
        return this;
    }

    public void setWidget(T widget) {
        this.widget = widget;

        clear();
        add(widget);
    }


    @Override
    protected void onUnload() {
        super.onUnload();

        unload();
    }

    public void unload() {
        widgetMap.clear();
    }

    public AdaptiveWidgetMap<T> getWidgetMap() {
        return widgetMap;
    }

    public void setWidgetMap(AdaptiveWidgetMap<T> widgetMap) {
        this.widgetMap = widgetMap;
    }

    public Widget getWidget() {
        return widget;
    }
}
