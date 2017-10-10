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

import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.html.Label;
import gwt.material.design.client.ui.html.Span;

/**
 * Test case for Long Box
 *
 * @author kevzlou7979
 */
public class MaterialSwitchTest extends MaterialWidgetTest<MaterialSwitch> {

    @Override
    protected MaterialSwitch createWidget() {
        return new MaterialSwitch();
    }

    @Override
    public void testInitialClasses() {
        checkInitialClasses(CssName.SWITCH);
    }

    @Override
    public void testEnabled() {
        super.testEnabled();

        // given
        MaterialSwitch mSwitch = getWidget();

        // when / then
        Label label = (Label) mSwitch.getWidget(0);
        MaterialInput input = (MaterialInput) label.getWidget(1);

        mSwitch.setEnabled(true);
        assertTrue(mSwitch.isEnabled());
        assertTrue(input.isEnabled());

        mSwitch.setEnabled(false);
        assertFalse(mSwitch.isEnabled());
        assertFalse(input.isEnabled());
    }

    public void testStructure() {
        // given
        MaterialSwitch mSwitch = getWidget();

        // when / then
        assertTrue(mSwitch.getWidget(0) instanceof Label);
        Label label = (Label) mSwitch.getWidget(0);
        assertTrue(label.getWidget(0) instanceof Span);

        // Off Label checking
        final String OFF_LABEL = "Off";
        assertTrue(label.getWidget(0) instanceof Span);
        Span offLabel = (Span) label.getWidget(0);
        mSwitch.setOffLabel(OFF_LABEL);
        assertEquals(OFF_LABEL, offLabel.getText());

        // Input Element checking
        assertTrue(label.getWidget(1) instanceof MaterialInput);
        MaterialInput input = (MaterialInput) label.getWidget(1);

        assertFalse(input.getElement().hasAttribute("checked"));
        mSwitch.setValue(false);
        assertFalse(input.getElement().hasAttribute("checked"));
        mSwitch.setValue(true);
        assertTrue(input.getElement().hasAttribute("checked"));

        // Lever Element checking
        assertTrue(label.getWidget(2) instanceof Span);
        Span lever = (Span) label.getWidget(2);
        assertTrue(lever.getElement().hasClassName(CssName.LEVER));

        // On Label checking
        final String ON_LABEL = "On";
        assertTrue(label.getWidget(3) instanceof Span);
        Span onLabel = (Span) label.getWidget(3);
        mSwitch.setOnLabel(ON_LABEL);
        assertEquals(ON_LABEL, onLabel.getText());
    }

    public void testValueChangeEvent() {
        // given
        MaterialSwitch mSwitch = getWidget();

        // when / then
        checkValueChangeEvent(mSwitch, true, false);
    }


    public void testErrorSuccess() {
        // given
        MaterialSwitch mSwitch = getWidget();

        // when / then
        checkFieldErrorSuccess(mSwitch, mSwitch.getErrorLabel());
    }
}
