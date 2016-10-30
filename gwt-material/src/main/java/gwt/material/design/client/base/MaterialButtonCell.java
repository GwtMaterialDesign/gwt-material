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
package gwt.material.design.client.base;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.DOM;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.MaterialButton;

import static com.google.gwt.dom.client.BrowserEvents.CLICK;

public class MaterialButtonCell extends AbstractCell<MaterialButton> {

    public MaterialButtonCell() {
        super("click", "keydown");
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, MaterialButton value, NativeEvent event, ValueUpdater<MaterialButton> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueUpdater);
        if (CLICK.equals(event.getType())) {
            EventTarget eventTarget = event.getEventTarget();
            if (!Element.is(eventTarget)) {
                return;
            }
            if (parent.getFirstChildElement().isOrHasChild(Element.as(eventTarget))) {
                // Ignore clicks that occur outside of the main element.
                onEnterKeyDown(context, parent, value, event, valueUpdater);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void render(Context context, MaterialButton value, SafeHtmlBuilder sb) {
        value.getIcon().addStyleName(CssName.MATERIAL_ICONS);
        sb.appendHtmlConstant(DOM.toString(value.getElement()));
    }

    @Override
    protected void onEnterKeyDown(Context context,
                                  Element parent,
                                  MaterialButton value,
                                  NativeEvent event,
                                  ValueUpdater<MaterialButton> valueUpdater) {
        if (valueUpdater != null) {
            valueUpdater.update(value);
        }
    }
}
