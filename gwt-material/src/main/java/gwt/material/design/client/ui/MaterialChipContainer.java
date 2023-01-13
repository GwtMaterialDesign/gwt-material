/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2020 GwtMaterialDesign
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
package gwt.material.design.client.ui;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.DefaultMoreChipHandler;
import gwt.material.design.client.base.MoreChipHandler;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;

import java.util.ArrayList;
import java.util.List;

public class MaterialChipContainer extends MaterialPanel implements HasSelectionHandlers<List<MaterialChip>> {

    private MoreChipHandler chipHandler = new DefaultMoreChipHandler(this);
    private List<MaterialChip> chipList = new ArrayList();
    private List<MaterialChip> selected = new ArrayList();
    private boolean multiple = true;
    private boolean enableToggle = true;

    public MaterialChipContainer() {
        super("chip-container");
    }

    protected void onLoad() {
        super.onLoad();

        chipHandler.load();
    }

    protected void add(Widget child, Element container) {
        super.add(child, container);

        if (child instanceof MaterialChip) {
            MaterialChip chip = (MaterialChip) child;
            chip.setTabIndex(0);
            chip.registerHandler(chip.addClickHandler((event) -> {
                if (isEnableToggle()) {
                    toggle(chip);
                }
            }));
            chip.registerHandler(chip.addKeyUpHandler((event) -> {
                if (event.getNativeKeyCode() == 13) {
                    toggle(chip);
                }

            }));
            chipHandler.update(chip);
            chipList.add(chip);
        }
    }

    protected void insert(Widget child, Element container, int beforeIndex, boolean domInsert) {
        super.insert(child, container, beforeIndex, domInsert);

        if (child instanceof MaterialChip) {
            chipList.add(beforeIndex, (MaterialChip) child);
            chipHandler.update((MaterialChip) child);
        }
    }

    public boolean remove(Widget w) {
        if (w instanceof MaterialChip) {
            chipList.remove(w);
            super.remove(w);
            chipHandler.update((MaterialChip) w);
        }
        return true;
    }

    public void clear() {
        for (MaterialChip chip : chipList) {
            remove(chip);
        }
    }

    public void reload() {
        chipHandler.reload();
    }

    public void setActive(MaterialChip chip, boolean active) {
        if (!multiple) {
            clearActive();
        }
        chip.setActive(active);
        if (active && !selected.contains(chip)) {
            selected.add(chip);
        } else {
            selected.remove(chip);
        }

        SelectionEvent.fire(this, selected);
    }

    public void setVisibleChips(int visibleChips) {
        chipHandler.setVisibleChipsSize(visibleChips);
    }

    public void toggle(MaterialChip chip) {
        setActive(chip, !chip.isActive());
    }

    public void clearActive() {
        chipList.forEach((c) -> c.setActive(false));
    }

    public void collapse() {
        chipHandler.collapse();
    }

    public void expand() {
        chipHandler.expand();
    }

    public void setCollapsible(boolean enableCollapsible) {
        chipHandler.setCollapsible(enableCollapsible);
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public boolean isEnableToggle() {
        return enableToggle;
    }

    public void setEnableToggle(boolean enableToggle) {
        this.enableToggle = enableToggle;
    }

    public List<MaterialChip> getChipList() {
        return chipList;
    }

    public List<MaterialChip> getSelected() {
        return selected;
    }

    public HandlerRegistration addSelectionHandler(SelectionHandler<List<MaterialChip>> handler) {
        return addHandler(handler, SelectionEvent.getType());
    }
}
