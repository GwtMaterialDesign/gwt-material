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


import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.custom.Waves;
import gwt.material.design.client.custom.helper.StyleHelper;

/**
 * @author Ben Dol
 */
public class EnabledMixin<T extends UIObject & HasEnabled> extends AbstractMixin<T> implements HasEnabled {
    private static final String DISABLED = "disabled";

    private boolean hasWaves;

    public EnabledMixin(final T widget) {
        super(widget);
    }

    @Override
    public boolean isEnabled() {
        return !StyleHelper.containsStyle(uiObject.getStyleName(), "disabled");
    }

    @Override
    public void setEnabled(boolean enabled) {
        setEnabled(uiObject, enabled);
    }

    private void setEnabled(UIObject obj, boolean enabled) {
        if(enabled) {
            obj.removeStyleName("disabled");
            obj.removeStyleName("grey lighten-2");
            obj.getElement().removeAttribute(DISABLED);

            if(hasWaves) {
                obj.addStyleName(Waves.WAVES_STYLE);
            }
        } else {
            obj.addStyleName("disabled");
            obj.addStyleName("grey lighten-2");
            obj.getElement().setAttribute(DISABLED, "");

            hasWaves = StyleHelper.containsStyle(obj.getStyleName(), Waves.WAVES_STYLE);
            if(hasWaves) {
                obj.removeStyleName(Waves.WAVES_STYLE);
            }
        }

        if(obj instanceof HasWidgets) {
            for(Widget widget : (HasWidgets)obj) {
                if(widget instanceof HasEnabled) {
                    setEnabled(widget, enabled);
                }
            }
        }
    }
}
