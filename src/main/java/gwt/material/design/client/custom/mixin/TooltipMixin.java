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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.custom.HasTooltip;
import gwt.material.design.client.custom.Tooltip;

/**
 * @author Ben Dol
 */
public class TooltipMixin<T extends Widget & HasTooltip> extends AbstractMixin<T> implements HasTooltip {

    private Tooltip tooltip;

    public TooltipMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setTooltip(String tooltip) {
        setTooltip(new Tooltip(tooltip));
    }

    @Override
    public Tooltip getTooltip() {
        return tooltip;
    }

    @Override
    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;

        Element element = uiObject.getElement();
        if(tooltip != null) {
            uiObject.addStyleName("tooltipped");
            element.setAttribute("data-tooltip", tooltip.getText());
            element.setAttribute("data-position", tooltip.getPosition().getCssName());
            element.setAttribute("data-delay", String.valueOf(tooltip.getDelay()));
        } else {
            uiObject.removeStyleName("tooltipped");
            element.removeAttribute("data-tooltip");
            element.removeAttribute("data-position");
            element.removeAttribute("data-delay");
        }
        Tooltip.detectAndApply(uiObject);
    }
}
