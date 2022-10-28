/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2022 GwtMaterialDesign
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

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialChipContainer;
import gwt.material.design.client.ui.MaterialLink;

import java.util.List;

import static gwt.material.design.client.js.JsMaterialElement.$;

public class DefaultMoreChipHandler implements MoreChipHandler {

    protected int visibleChipsSize = 0;
    protected MaterialLink more = new MaterialLink();
    protected String localizedMoreText = "Show {0} more items";
    protected MaterialChipContainer container;
    protected ToggleStyleMixin<MaterialChipContainer> toggleStyleMixin;
    protected HandlerRegistration handlerRegistration;

    public DefaultMoreChipHandler() {
        more.setDisplay(Display.BLOCK);
        more.setMarginLeft(4);
        more.setMarginTop(12);
    }

    @Override
    public void setVisibleChipsSize(int visibleChipsSize) {
        this.visibleChipsSize = visibleChipsSize;
    }

    @Override
    public void load(MaterialChipContainer container) {
        this.container = container;

        showHiddenChips(false);
    }

    @Override
    public void reload() {
        showHiddenChips(false);
    }

    public void showHiddenChips(boolean showHiddenChips) {
        if (handlerRegistration == null) {
            handlerRegistration = more.addClickHandler(clickEvent -> showHiddenChips(!getToggleStyleMixin().isOn()));
        }
        if (!more.isAttached()) {
            container.add(more);
        }
        List<MaterialChip> chipList = container.getChipList();
        int hiddenChipsSize = chipList.size() - visibleChipsSize;
        for (MaterialChip widgets : chipList) {
            if (showHiddenChips) {
                $(widgets.getElement()).css("display", "block");
            } else {
                if (chipList.indexOf(widgets) > visibleChipsSize) {
                    $(widgets.getElement()).css("display", "none");
                }
            }
        }

        if (showHiddenChips) {
            more.setVisibility(Style.Visibility.HIDDEN);
        } else {
            more.setVisibility(Style.Visibility.VISIBLE);
            more.setText(localizedMoreText.replace("{0}", hiddenChipsSize + ""));
        }
    }

    public ToggleStyleMixin<MaterialChipContainer> getToggleStyleMixin() {
        if (toggleStyleMixin == null) {
            toggleStyleMixin = new ToggleStyleMixin<>(container, "expanded");
        }
        return toggleStyleMixin;
    }
}
