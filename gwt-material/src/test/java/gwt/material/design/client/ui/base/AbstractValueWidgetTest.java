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
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.HasError;
import gwt.material.design.client.base.HasPlaceholder;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.MaterialSwitch;

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

    protected <T extends MaterialWidget & HasValue<Boolean>> void checkBooleanValue(T widget) {
        
        // Register value change handler that listens when the widget
        // set the value
        final boolean[] isValueChanged = {false};
        widget.addValueChangeHandler(event -> isValueChanged[0] = true);

        // By default setValue(boolean) will not fire the value change event.
        widget.setValue(true);
        assertTrue(widget.getValue());
        // Expected result : false
        assertEquals(isValueChanged[0], false);
        // Expected result : false
        widget.setValue(false, false);
        assertFalse(widget.getValue());
        // Expected result : true
        widget.setValue(true, true);
        assertTrue(widget.getValue());
        widget.getValue();
    }

    protected <T extends MaterialWidget> void fireValueChangeEvent(T widget) {
        widget.fireEvent(new GwtEvent<ValueChangeHandler<?>>() {
            @Override
            public Type<ValueChangeHandler<?>> getAssociatedType() {
                return ValueChangeEvent.getType();
            }

            @Override
            protected void dispatch(ValueChangeHandler eventHandler) {
                eventHandler.onValueChange(null);
            }
        });
    }
}
