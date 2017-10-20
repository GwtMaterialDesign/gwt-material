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
package gwt.material.design.client.base.mixin;

import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.base.HasOrientation;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Orientation;
import gwt.material.design.client.events.OrientationChangeEvent;
import gwt.material.design.client.js.Window;
import gwt.material.design.client.ui.MaterialToast;

/**
 *  @author kevzlou7979
 */
public class OrientationMixin<T extends MaterialWidget & HasOrientation> extends AbstractMixin<T>  implements HasOrientation {

    private boolean detectOrientation = false;
    private HandlerRegistration orientationHandler;
    private Orientation orientation;
    private CssNameMixin<MaterialWidget, Orientation> nameMixin;

    public OrientationMixin(T widget) {
        super(widget);
    }

    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
        if (orientation != null) {
            OrientationChangeEvent.fire(uiObject, orientation);
            getNameMixin().setCssName(orientation);
        }
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public void setDetectOrientation(boolean detectOrientation) {
        this.detectOrientation = detectOrientation;

        if(orientationHandler != null) {
            orientationHandler.removeHandler();
            orientationHandler = null;
        }

        if(detectOrientation) {
            orientationHandler = Window.addResizeHandler(resizeEvent -> detectAndApplyOrientation());
            detectAndApplyOrientation();
        }
    }

    protected void detectAndApplyOrientation() {
        if (Window.matchMedia("(orientation: portrait)")) {
            setOrientation(Orientation.PORTRAIT);
        } else {
            setOrientation(Orientation.LANDSCAPE);
        }
    }

    @Override
    public boolean isDetectOrientation() {
        return detectOrientation;
    }

    @Override
    public HandlerRegistration addOrientationChangeHandler(OrientationChangeEvent.OrientationChangeHandler handler) {
        return uiObject.addHandler(handler, OrientationChangeEvent.TYPE);
    }

    public CssNameMixin<MaterialWidget, Orientation> getNameMixin() {
        if (nameMixin == null) {
            nameMixin = new CssNameMixin<>(uiObject);
        }
        return nameMixin;
    }
}
