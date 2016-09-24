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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Element;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for ValueBox
 *
 * @author kevzlou7979
 */
public class MaterialValueBoxTest extends MaterialWidgetTest {

    public <T extends MaterialValueBox> void checkValueBox(T widget) {
        checkWidget(widget);
        checkValue(widget);
        checkErrorSuccess(widget);
        checkPlaceholder(widget);
        checkIcon(widget);
    }

    public <T extends MaterialValueBox> void checkValue(T widget) {
        if (widget instanceof MaterialTextBox || widget instanceof MaterialTextArea) {
            widget.setValue("Value");
            assertEquals(widget.getValue(), "Value");
        } else if (widget instanceof MaterialIntegerBox) {
            widget.setValue(123);
            //assertNotNull(widget.getValue());
        } else if (widget instanceof MaterialFloatBox) {
            widget.setValue(123f);
        } else if (widget instanceof MaterialDoubleBox) {
            widget.setValue(123.00);
        } else if (widget instanceof MaterialLongBox) {
            widget.setValue(123000l);
        }
    }

    public <T extends MaterialValueBox> void checkErrorSuccess(T widget) {
        Element valueBox = widget.getWidget(0).getElement();
        widget.setError("Error");
        assertTrue(valueBox.hasClassName(CssName.INVALID));
        widget.setSuccess("Success");
        assertTrue(valueBox.hasClassName(CssName.VALID));
        widget.clearErrorOrSuccess();
        assertFalse(valueBox.hasClassName(CssName.VALID));
        assertFalse(valueBox.hasClassName(CssName.INVALID));
    }

    public <T extends MaterialValueBox> void checkPlaceholder(T widget) {
        widget.setPlaceholder("Placeholder");
        assertEquals(widget.getPlaceholder(), "Placeholder");
        widget.setPlaceholder("");
        assertEquals(widget.getPlaceholder(), "");
    }

    public <T extends MaterialValueBox> void checkIcon(T widget) {
        Element iconElement = widget.getIcon().getElement();
        widget.setIconType(IconType.POLYMER);
        assertEquals(iconElement.getInnerHTML(), IconType.POLYMER.getCssName());
        widget.setIconColor(Color.RED);
        assertTrue(iconElement.hasClassName(Color.RED.getCssName() + "-text"));
        widget.setIconPosition(IconPosition.LEFT);
        assertTrue(iconElement.hasClassName(IconPosition.LEFT.getCssName()));
        widget.setIconPrefix(true);
        assertTrue(iconElement.hasClassName("prefix"));
        widget.setIconPrefix(false);
        assertFalse(iconElement.hasClassName("prefix"));
        widget.setIconSize(IconSize.LARGE);
        assertTrue(iconElement.hasClassName(IconSize.LARGE.getCssName()));
    }

    public <T extends MaterialValueBox> void checkReadOnly(T widget) {
        Element element = widget.getElement();
        widget.setReadOnly(true);
        assertTrue(element.hasAttribute("disabled"));
        assertTrue(element.hasClassName(CssName.READ_ONLY));
        assertEquals(widget.isReadOnly(), true);
        widget.setReadOnly(false);
        assertFalse(element.hasAttribute("disabled"));
        assertFalse(element.hasClassName(CssName.READ_ONLY));
        assertEquals(widget.isReadOnly(), false);

        widget.setToggleReadOnly(true);
        assertTrue(element.addClassName(CssName.READ_ONLY_TOGGLE));
        assertEquals(widget.isToggleReadOnly(), true);
        widget.setToggleReadOnly(false);
        assertFalse(element.addClassName(CssName.READ_ONLY_TOGGLE));
        assertEquals(widget.isToggleReadOnly(), false);
    }
}
