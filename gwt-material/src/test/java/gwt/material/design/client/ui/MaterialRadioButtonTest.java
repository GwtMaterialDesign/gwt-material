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
import gwt.material.design.client.constants.RadioButtonType;
import gwt.material.design.client.ui.base.AbstractValueWidgetTest;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Radio Button
 *
 * @author kevzlou7979
 */
public class MaterialRadioButtonTest extends AbstractValueWidgetTest {

    public void init() {
        MaterialRadioButton radioButton = new MaterialRadioButton();
        checkType(radioButton);
        checkValueChangeEvent(radioButton, true, false);
    }

    public <T extends MaterialRadioButton> void checkType(T radioButton) {
        assertNotNull(DOM.getChild(radioButton.getElement(), 0));
        Element element = DOM.getChild(radioButton.getElement(), 0);
        radioButton.setType(RadioButtonType.GAP);
        assertEquals(radioButton.getType(), RadioButtonType.GAP);
        assertTrue(element.hasClassName(RadioButtonType.GAP.getCssName()));
        radioButton.setType(RadioButtonType.NO_GAP);
        assertEquals(radioButton.getType(), RadioButtonType.NO_GAP);
        assertFalse(element.hasClassName(RadioButtonType.GAP.getCssName()));
        assertEquals(radioButton.getType().getCssName(), "");
    }
}
