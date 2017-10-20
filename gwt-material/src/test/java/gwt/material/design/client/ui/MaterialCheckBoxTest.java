/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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
import com.google.gwt.user.client.DOM;
import gwt.material.design.client.WidgetTestCase;
import gwt.material.design.client.constants.CheckBoxType;
import gwt.material.design.client.constants.CssName;

/**
 * Test case for Checkbox.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialCheckBoxTest extends WidgetTestCase<MaterialCheckBox> {

    @Override
    protected MaterialCheckBox createWidget() {
        return new MaterialCheckBox();
    }

    public void testValue() {
        // given
        MaterialCheckBox checkbox = getWidget();

        // when / then
        checkbox.setText("test");
        assertEquals("test", checkbox.getText());
        assertFalse(checkbox.getValue());
        checkbox.setValue(true);
        assertTrue(checkbox.getValue());
        checkbox.setValue(false);
        assertFalse(checkbox.getValue());
        checkbox.setValue(true, true);
        checkbox.addValueChangeHandler(valueChangeEvent -> {
            assertEquals(valueChangeEvent.getValue(), checkbox.getValue());
        });
    }

    public void testType() {
        // UiBinder
        // given
        MaterialCheckBox widget = getWidget(false);

        // when / then
        widget.setType(CheckBoxType.FILLED);
        assertEquals(CheckBoxType.FILLED, widget.getType());
        widget.setType(CheckBoxType.INTERMEDIATE);
        assertEquals(CheckBoxType.INTERMEDIATE, widget.getType());

        // Standard
        // given
        attachWidget();

        // when / then
        widget.setType(CheckBoxType.FILLED);
        Element cb = widget.getElement();
        Element input = DOM.getChild(cb, 0);
        assertTrue(input.hasClassName(CssName.FILLED_IN));
        widget.setType(CheckBoxType.INTERMEDIATE);
        assertTrue(widget.getElement().hasClassName(CheckBoxType.INTERMEDIATE.getCssName() + "-checkbox"));
    }
}
