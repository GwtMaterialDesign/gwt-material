/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasDensity;
import gwt.material.design.client.base.density.Density;
import gwt.material.design.client.base.density.DisplayDensity;
import gwt.material.design.client.base.helper.EventHelper;

public class DensityMixin<T extends Widget> extends AbstractMixin<T> implements HasDensity {

    private CssNameMixin<Widget, Density> styleMixin;
    private Density density = DisplayDensity.DEFAULT;

    public DensityMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void setDensity(Density density) {
        this.density = density;

        if (uiObject.isAttached()) {
            getStyleMixin().setCssName(density);
        } else {
            EventHelper.onAttachOnce(uiObject, attachEvent -> getStyleMixin().setCssName(density));
        }
    }

    @Override
    public Density getDensity() {
        return density;
    }

    public CssNameMixin<Widget, Density> getStyleMixin() {
        if (styleMixin == null) {
            styleMixin = new CssNameMixin<>(uiObject);
        }
        return styleMixin;
    }
}
