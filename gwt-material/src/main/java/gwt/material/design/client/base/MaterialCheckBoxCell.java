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

import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class MaterialCheckBoxCell extends AbstractEditableCell<Boolean, Boolean> {

    private final boolean dependsOnSelection;
    private final boolean handlesSelection;

    public MaterialCheckBoxCell() {
        this(false);
    }

    @Deprecated
    public MaterialCheckBoxCell(boolean isSelectBox) {
        this(isSelectBox, isSelectBox);
    }

    public MaterialCheckBoxCell(boolean dependsOnSelection, boolean handlesSelection) {
        super(BrowserEvents.CHANGE, BrowserEvents.KEYDOWN, BrowserEvents.CLICK);
        this.dependsOnSelection = dependsOnSelection;
        this.handlesSelection = handlesSelection;
    }

    @Override
    public boolean dependsOnSelection() {
        return dependsOnSelection;
    }

    @Override
    public boolean handlesSelection() {
        return handlesSelection;
    }

    @Override
    public boolean isEditing(Context context, Element parent, Boolean value) {
        // A checkbox is never in "edit mode". There is no intermediate state
        // between checked and unchecked.
        return false;
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, Boolean value, NativeEvent event, ValueUpdater<Boolean> valueUpdater) {
        String type = event.getType();

        boolean enterPressed = (BrowserEvents.KEYDOWN.equals(type) && event.getKeyCode() == KeyCodes.KEY_ENTER)
                || event.getType().equals(BrowserEvents.CLICK);

        if (BrowserEvents.CHANGE.equals(type) || enterPressed) {
            InputElement input = parent.getFirstChild().getFirstChild().cast();
            Boolean isChecked = input.isChecked();

            /*
             * Toggle the value if the enter key was pressed and the cell handles selection or doesn't depend on selection. If the cell depends on selection but doesn't handle selection, then ignore
             * the enter key and let the SelectionEventManager determine which keys will trigger a change.
             */
            if (enterPressed && (handlesSelection() || !dependsOnSelection())) {
                isChecked = !isChecked;
                input.setChecked(isChecked);
            }

            /*
             * Save the new value. However, if the cell depends on the selection, then do not save the value because we can get into an inconsistent state.
             */
            if (value != isChecked && !dependsOnSelection()) {
                setViewData(context.getKey(), isChecked);
            } else {
                clearViewData(context.getKey());
            }

            if (valueUpdater != null) {
                valueUpdater.update(isChecked);
            }
        }
    }

    @Override
    public void render(Context context, Boolean value, SafeHtmlBuilder sb) {
        Object key = context.getKey();
        Boolean viewData = getViewData(key);
        if (viewData != null && viewData.equals(value)) {
            clearViewData(key);
            viewData = null;
        }

        String state = "";
        if (value != null && ((viewData != null) ? viewData : value)) {
            state = "checked";
        }
        String id = Document.get().createUniqueId();

        sb.append(SafeHtmlUtils.fromSafeConstant(
                "<span class=\"gwt-CheckBox\">" +
                        "<input type=\"checkbox\" class=\"filled-in\" tabindex=\"-1\" value=\"on\" id=\"" + id + "\" " + state + "/>" +
                        "<label for=\"" + id + "\"></label>" +
                        "</span>"));

    }
}
