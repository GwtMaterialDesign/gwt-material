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
import com.google.gwt.user.client.DOM;
import gwt.material.design.client.constants.CheckBoxType;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Checkbox
 *
 * @author kevzlou7979
 */
public class MaterialCheckBoxTest extends MaterialWidgetTest {

    public void init() {
        MaterialCheckBox checkBox = new MaterialCheckBox();
        checkType(checkBox);
        checkValue(checkBox);
    }

    public <T extends MaterialCheckBox> void checkValue(T checkbox) {
        checkbox.setText("test");
        assertEquals(checkbox.getText(), "test");
        assertFalse(checkbox.getValue());
        checkbox.setValue(true);
        assertTrue(checkbox.getValue());
        checkbox.setValue(false);
        assertFalse(checkbox.getValue());
        checkbox.setValue(true, true);
        checkbox.addValueChangeHandler(valueChangeEvent -> {
            assertEquals(checkbox.getValue(), valueChangeEvent.getValue());
        });
    }

    public <T extends MaterialCheckBox> void checkType(T checkbox) {
        checkbox.setType(CheckBoxType.FILLED);
        Element cb = checkbox.getElement();
        Element input = DOM.getChild(cb, 0);
        assertTrue(input.hasClassName(CssName.FILLED_IN));
        checkbox.setType(CheckBoxType.INTERMEDIATE);
        assertTrue(checkbox.getElement().hasClassName(CheckBoxType.INTERMEDIATE.getCssName() + "-checkbox"));
    }
}
