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
package gwt.material.design.client.ui.base;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.*;
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.HasError;
import gwt.material.design.client.base.HasPlaceholder;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialListValueBox;
import gwt.material.design.client.ui.MaterialSwitch;
import gwt.material.design.client.ui.dto.User;

/**
 * Test case for Abstract value widget
 *
 * @author kevzlou7979
 */
public class AbstractValueWidgetTest extends MaterialWidgetTest {

    public <T extends AbstractValueWidget & HasPlaceholder, H extends UIObject> void checkAbstractValueWidget(T widget, H target) {
        checkWidget(widget);
        checkErrorSuccess(widget, target);
        checkPlaceholder(widget);
    }

    public <T extends AbstractValueWidget, H extends UIObject> void checkAbstractValueWidgetWoPlaceholder(T widget, H target) {
        checkWidget(widget);
        checkErrorSuccess(widget, target);
    }

    protected <T extends MaterialWidget & HasError, H extends UIObject> void checkErrorSuccess(T widget, H target) {
        assertNotNull(target);
        widget.setError("Error");
        assertTrue(target.getElement().hasClassName(CssName.INVALID));
        widget.setSuccess("Success");
        assertTrue(target.getElement().hasClassName(CssName.VALID));
        widget.clearErrorOrSuccess();
        assertFalse(target.getElement().hasClassName(CssName.VALID));
        assertFalse(target.getElement().hasClassName(CssName.INVALID));
    }

    protected <T extends MaterialWidget & HasError, H extends UIObject, L extends UIObject> void checkLabelErrorSuccess(T widget, H target, L label) {
        if (target != null) {
            checkErrorSuccess(widget, target);
        }

        final String ERROR = "error";
        Element element = label.getElement();
        widget.setError(ERROR);
        assertTrue(element.hasClassName(CssName.FIELD_ERROR_LABEL));
        assertFalse(element.hasClassName(CssName.FIELD_SUCCESS_LABEL));
        assertFalse(element.hasClassName(CssName.FIELD_HELPER_LABEL));
        assertEquals(label.getElement().getInnerText(), ERROR);

        final String SUCCESS = "success";
        widget.setSuccess(SUCCESS);
        assertTrue(element.hasClassName(CssName.FIELD_SUCCESS_LABEL));
        assertFalse(element.hasClassName(CssName.FIELD_ERROR_LABEL));
        assertFalse(element.hasClassName(CssName.FIELD_HELPER_LABEL));
        assertEquals(label.getElement().getInnerText(), SUCCESS);

        final String INFO = "info";
        widget.setHelperText(INFO);
        assertTrue(element.hasClassName(CssName.FIELD_HELPER_LABEL));
        assertFalse(element.hasClassName(CssName.FIELD_SUCCESS_LABEL));
        assertFalse(element.hasClassName(CssName.FIELD_ERROR_LABEL));
        assertEquals(label.getElement().getInnerText(), INFO);

    }

    protected <T extends HasPlaceholder> void checkPlaceholder(T widget) {
        widget.setPlaceholder("Placeholder");
        assertEquals(widget.getPlaceholder(), "Placeholder");
        widget.setPlaceholder("");
        assertEquals(widget.getPlaceholder(), "");
    }

    protected <T extends Widget & HasValue & HasEnabled> void checkValueChangeEvent(T widget, Object value, Object secondValue) {
        RootPanel.get().add(widget);
        assertNotSame(value, secondValue);
        // Widget must be enabled before firing the event
        widget.setEnabled(true);
        assertTrue(widget.isEnabled());
        // Ensure the widget is attached to the root panel
        assertTrue(widget.isAttached());
        // Register value change handler that listens when the widget
        // set the value
        final boolean[] isValueChanged = {false};
        widget.addValueChangeHandler(event -> isValueChanged[0] = true);
        // By default setValue(boolean) will not fire the value change event.
        widget.setValue(value);
        assertEquals(widget.getValue(), value);
        // Expected result : false
        assertFalse(isValueChanged[0]);
        // Calling setValue(value, fireEvents) with fireEvents set to false
        widget.setValue(secondValue, false);
        // Expected result : secondValue
        assertEquals(widget.getValue(), secondValue);
        // Expected result : false
        assertFalse(isValueChanged[0]);
        // Calling setValue(value, fireEvents) with fireEvents set to true
        widget.setValue(value, true);
        // Expected result : true
        assertTrue(isValueChanged[0]);
        // Expected result : value
        assertEquals(widget.getValue(), value);
    }
}
