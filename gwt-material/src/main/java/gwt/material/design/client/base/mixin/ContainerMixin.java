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
package gwt.material.design.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasContainer;
import gwt.material.design.client.constants.CssName;

public class ContainerMixin<T extends UIObject & HasContainer> extends AbstractMixin<T>
    implements HasContainer {

    private ToggleStyleMixin<T> containerEnabledMixin;
    private ToggleStyleMixin<T> valignWrapperMixin;

    public ContainerMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void setContainerEnabled(boolean value) {
        getContainerEnabledMixin().setOn(value);
    }

    @Override
    public boolean isContainerEnabed() {
        return getContainerEnabledMixin().isOn();
    }

    @Override
    public void setValignWrapper(boolean value) {
        getValignWrapperMixin().setOn(value);
    }

    @Override
    public boolean isValignWrapper() {
        return getValignWrapperMixin().isOn();
    }

    protected ToggleStyleMixin<T> getContainerEnabledMixin() {
        if (containerEnabledMixin == null) {
            containerEnabledMixin = new ToggleStyleMixin(uiObject, CssName.CONTAINER);
        }
        return containerEnabledMixin;
    }

    protected ToggleStyleMixin<T> getValignWrapperMixin() {
        if (valignWrapperMixin == null) {
            valignWrapperMixin = new ToggleStyleMixin<>(uiObject, CssName.VALIGN_WRAPPER);
        }
        return valignWrapperMixin;
    }
}
