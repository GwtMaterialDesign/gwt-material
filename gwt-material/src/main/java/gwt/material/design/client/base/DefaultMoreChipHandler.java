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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;

import java.util.List;

import static gwt.material.design.client.js.JsMaterialElement.$;

public class DefaultMoreChipHandler implements MoreChipHandler {

    protected int visibleChipsSize = 0;
    protected MaterialLink more = new MaterialLink();
    protected String localizedMoreText = "Show {0} more items";
    protected MaterialChipContainer container;
    protected ToggleStyleMixin<MaterialChipContainer> toggleStyleMixin;
    protected HandlerRegistration handlerRegistration;
    protected MaterialLabel collapseLabel = new MaterialLabel();
    protected MaterialIcon collapseIcon = new MaterialIcon(IconType.KEYBOARD_ARROW_UP);
    protected ToggleStyleMixin<Widget> collapsibleMixin;
    protected ToggleStyleMixin<Widget> collapseMixin;

    public DefaultMoreChipHandler(MaterialChipContainer container) {
        this.container = container;
        more.setDisplay(Display.BLOCK);
        more.setMarginLeft(4);
        more.setMarginTop(12);
    }

    @Override
    public void setVisibleChipsSize(int visibleChipsSize) {
        this.visibleChipsSize = visibleChipsSize;
    }

    @Override
    public void load() {
        showHiddenChips(false);
        collapseIcon.addStyleName("collapse");
        collapseLabel.addStyleName("collapse-label");
        collapseIcon.addClickHandler(this::toggle);
        collapseLabel.addClickHandler(this::toggle);
        if (!collapseIcon.isAttached()) {
            collapseIcon.setVisible(false);
            collapseLabel.setVisible(false);
            container.add(collapseIcon);
            container.add(collapseLabel);
        }
    }

    public void collapse() {
        getCollapseMixin().setOn(true);
    }

    public void expand() {
        getCollapseMixin().setOn(false);
    }

    public void setCollapsible(boolean enableCollapsible) {
        getCollapsibleMixin().setOn(enableCollapsible);
    }

    protected void toggle(ClickEvent event) {
        if (!getCollapseMixin().isOn()) {
            collapse();
        } else {
            expand();
        }
        event.stopPropagation();
        event.preventDefault();
    }

    @Override
    public void reload() {
        showHiddenChips(false);
    }

    @Override
    public void update(MaterialChip chip) {
        if (getCollapseMixin().isOn()) {
            MaterialIcon icon = chip.getIcon();
            icon.registerHandler(icon.addClickHandler(clickEvent -> updateCollapseLabel(chip)));
            updateCollapseLabel(chip);
        }
    }

    protected void updateCollapseLabel(MaterialChip chip) {
        int length = $(container.getElement()).find(".chip").length();
        collapseIcon.setVisible(length > 0);
        collapseLabel.setVisible(length > 0);
        collapseLabel.setText(length + "");
    }

    public void showHiddenChips(boolean showHiddenChips) {
        if (visibleChipsSize > 0) {
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
    }

    public ToggleStyleMixin<Widget> getCollapseMixin() {
        if (collapseMixin == null) {
            collapseMixin = new ToggleStyleMixin<>(container, "collapse");
        }
        return collapseMixin;
    }

    public ToggleStyleMixin<Widget> getCollapsibleMixin() {
        if (collapsibleMixin == null) {
            collapsibleMixin = new ToggleStyleMixin<>(container, "enable-collapsible");
        }
        return collapsibleMixin;
    }

    public ToggleStyleMixin<MaterialChipContainer> getToggleStyleMixin() {
        if (toggleStyleMixin == null) {
            toggleStyleMixin = new ToggleStyleMixin<>(container, "expanded");
        }
        return toggleStyleMixin;
    }
}
