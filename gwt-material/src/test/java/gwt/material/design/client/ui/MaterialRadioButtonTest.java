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
import gwt.material.design.client.constants.RadioButtonType;

/**
 * Test case for Radio Button
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialRadioButtonTest extends WidgetTestCase<MaterialRadioButton> {

    @Override
    protected MaterialRadioButton createWidget() {
        return new MaterialRadioButton();
    }

    public void testValueChangeEvent() {
        // given
        MaterialRadioButton radioButton = getWidget();

        // when / then
        checkValueChangeEvent(radioButton, true, false);
    }

    public void testType() {
        // UiBinder
        // given
        MaterialRadioButton radioButton = getWidget(false);

        // when / then
        radioButton.setType(RadioButtonType.GAP);
        assertEquals(RadioButtonType.GAP, radioButton.getType());
        radioButton.setType(RadioButtonType.NO_GAP);
        assertEquals(RadioButtonType.NO_GAP, radioButton.getType());
        assertEquals("", radioButton.getType().getCssName());

        // Standard
        // given
        attachWidget();

        // when / then
        assertNotNull(DOM.getChild(radioButton.getElement(), 0));
        Element element = DOM.getChild(radioButton.getElement(), 0);
        radioButton.setType(RadioButtonType.GAP);
        assertTrue(element.hasClassName(RadioButtonType.GAP.getCssName()));
        radioButton.setType(RadioButtonType.NO_GAP);
        assertFalse(element.hasClassName(RadioButtonType.GAP.getCssName()));
        assertEquals("", radioButton.getType().getCssName());
    }
}
