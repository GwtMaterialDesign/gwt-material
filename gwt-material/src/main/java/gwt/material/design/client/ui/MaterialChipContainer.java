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

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

public class MaterialChipContainer extends MaterialPanel {

    private List<MaterialChip> chipList = new ArrayList<>();
    private boolean enableToggle = true;

    public MaterialChipContainer() {
        super("chip-container");
    }

    @Override
    protected void add(Widget child, Element container) {
        super.add(child, container);

        if (child instanceof MaterialChip) {
            MaterialChip chip = (MaterialChip) child;
            chip.setTabIndex(0);
            chip.registerHandler(chip.addClickHandler(event -> {
                if (isEnableToggle()) toggle(chip);
            }));
            chip.registerHandler(chip.addKeyUpHandler(event -> {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) toggle(chip);
            }));
            chipList.add(chip);
        }
    }

    @Override
    protected void insert(Widget child, Element container, int beforeIndex, boolean domInsert) {
        super.insert(child, container, beforeIndex, domInsert);

        if (child instanceof MaterialChip) {
            chipList.add(beforeIndex, (MaterialChip) child);
        }
    }

    @Override
    public boolean remove(Widget w) {
        if (w instanceof MaterialChip) {
            chipList.remove(w);
        }
        return super.remove(w);
    }

    @Override
    public void clear() {
        super.clear();

        chipList.clear();
    }

    public void setActive(MaterialChip chip) {
        clearActive();
        chip.setActive(true);
    }

    public void setActive(int index) {
        clearActive();
        MaterialChip chip = chipList.get(index);
        if (chip != null) {
            setActive(chip);
        }
    }

    public void toggle(MaterialChip chip) {
        if (isEnableToggle()) setActive(chip);
    }

    public void clearActive() {
        chipList.forEach(c -> c.setActive(false));
    }

    public boolean isEnableToggle() {
        return enableToggle;
    }

    public void setEnableToggle(boolean enableToggle) {
        this.enableToggle = enableToggle;
    }
}
