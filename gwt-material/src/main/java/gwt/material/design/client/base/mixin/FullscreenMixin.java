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

import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasFullscreen;
import gwt.material.design.client.constants.CssName;

public class FullscreenMixin<T extends UIObject & HasFullscreen> extends AbstractMixin<T> implements HasFullscreen {


    private ToggleStyleMixin toggleStyleMixin = new ToggleStyleMixin<>(uiObject, CssName.FULLSCREEN);

    public FullscreenMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void setFullscreen(boolean value) {
        toggleStyleMixin.setOn(value);
    }

    @Override
    public boolean isFullscreen() {
        return toggleStyleMixin.isOn();
    }
}
