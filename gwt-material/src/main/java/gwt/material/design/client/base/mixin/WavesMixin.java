/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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

import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasWaves;
import gwt.material.design.client.base.Waves;
import gwt.material.design.client.constants.WavesType;

/**
 * @author Ben Dol
 */
public class WavesMixin<T extends Widget & HasWaves> extends AbstractMixin<T> implements HasWaves {

    private WavesType waves;

    public WavesMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setUiObject(T uiObject) {
        super.setUiObject(uiObject);

        // Apply waves to the ui object
        setWaves(waves);
    }

    @Override
    public void setWaves(WavesType waves) {
        uiObject.removeStyleName(Waves.WAVES_STYLE);
        if (this.waves != null) {
            uiObject.removeStyleName(this.waves.getCssName());
        }

        this.waves = waves;

        if (waves != null) {
            boolean enabled = !(uiObject instanceof HasEnabled) || ((HasEnabled) uiObject).isEnabled();
            if (enabled) {
                uiObject.addStyleName(Waves.WAVES_STYLE);
            }

            uiObject.addStyleName(waves.getCssName());
            if (enabled) {
                Waves.detectAndApply(uiObject);
            }
        }
    }

    @Override
    public WavesType getWaves() {
        return waves;
    }
}
