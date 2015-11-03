package gwt.material.design.client.custom.mixin;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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


import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.custom.HasWaves;
import gwt.material.design.client.custom.Waves;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ben Dol
 */
public class WavesMixin<T extends Widget & HasWaves> extends AbstractMixin<T> implements HasWaves {

    private WavesType waves;

    public WavesMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setWaves(WavesType waves) {
        uiObject.removeStyleName(Waves.WAVES_STYLE);
        if(this.waves != null) {
            uiObject.removeStyleName("waves-" + this.waves.getCssName());
        }

        this.waves = waves;

        if(waves != null) {
            uiObject.addStyleName(Waves.WAVES_STYLE);
            uiObject.addStyleName("waves-" + waves.getCssName());
            Waves.detectAndApply(uiObject);

            // Scan the top layer for more Wave widgets
            if(uiObject instanceof HasWidgets) {
                for(Widget w : (HasWidgets)uiObject) {
                    if(w instanceof HasWaves && !w.equals(this.uiObject)) {
                        ((HasWaves) w).setWaves(waves);
                    }
                }
            }
        }
    }

    @Override
    public WavesType getWaves() {
        return waves;
    }

    private void setWaves(T widget, WavesType waves) {

    }
}
