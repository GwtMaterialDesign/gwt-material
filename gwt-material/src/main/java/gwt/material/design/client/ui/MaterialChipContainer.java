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

import java.util.ArrayList;
import java.util.List;

public class MaterialChipContainer extends MaterialPanel implements HasSelectionHandlers<List<MaterialChip>> {
    private MoreChipHandler chipHandler = new DefaultMoreChipHandler();
    private List<MaterialChip> chipList = new ArrayList();
    private List<MaterialChip> selected = new ArrayList();
    private boolean multiple = true;
    private boolean enableToggle = true;

    public MaterialChipContainer() {
        super(new String[]{"chip-container"});
    }

    protected void onLoad() {
        super.onLoad();
        this.chipHandler.load(this);
    }

    protected void add(Widget child, Element container) {
        super.add(child, container);
        if (child instanceof MaterialChip) {
            MaterialChip chip = (MaterialChip)child;
            chip.setTabIndex(0);
            chip.registerHandler(chip.addClickHandler((event) -> {
                if (this.isEnableToggle()) {
                    this.toggle(chip);
                }

            }));
            chip.registerHandler(chip.addKeyUpHandler((event) -> {
                if (event.getNativeKeyCode() == 13) {
                    this.toggle(chip);
                }

            }));
            this.chipList.add(chip);
        }

    }

    protected void insert(Widget child, Element container, int beforeIndex, boolean domInsert) {
        super.insert(child, container, beforeIndex, domInsert);
        if (child instanceof MaterialChip) {
            this.chipList.add(beforeIndex, (MaterialChip)child);
        }

    }

    public boolean remove(Widget w) {
        if (w instanceof MaterialChip) {
            this.chipList.remove(w);
        }

        return super.remove(w);
    }

    public void clear() {
        super.clear();
        this.chipList.clear();
    }

    public void reload() {
        this.chipHandler.reload();
    }

    public void setActive(MaterialChip chip, boolean active) {
        if (!multiple) {
            clearActive();
        }
        chip.setActive(active);
        if (active && !this.selected.contains(chip)) {
            this.selected.add(chip);
        } else {
            this.selected.remove(chip);
        }

        SelectionEvent.fire(this, this.selected);
    }

    public void setVisibleChips(int visibleChips) {
        this.chipHandler.setVisibleChipsSize(visibleChips);
    }

    public void toggle(MaterialChip chip) {
        this.setActive(chip, !chip.isActive());
    }

    public void clearActive() {
        this.chipList.forEach((c) -> {
            c.setActive(false);
        });
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public boolean isEnableToggle() {
        return this.enableToggle;
    }

    public void setEnableToggle(boolean enableToggle) {
        this.enableToggle = enableToggle;
    }

    public List<MaterialChip> getChipList() {
        return this.chipList;
    }

    public List<MaterialChip> getSelected() {
        return this.selected;
    }

    public HandlerRegistration addSelectionHandler(SelectionHandler<List<MaterialChip>> handler) {
        return this.addHandler(handler, SelectionEvent.getType());
    }
}
