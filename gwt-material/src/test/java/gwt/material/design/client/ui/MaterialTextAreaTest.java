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

import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.InputType;

/**
 * Test case for Text Area.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialTextAreaTest extends MaterialValueBoxTest<MaterialTextArea> {

    @Override
    protected MaterialTextArea createWidget() {
        return new MaterialTextArea();
    }

    public void testStructure() {
        // given
        MaterialTextArea textArea = getWidget();

        // when / then
        assertTrue(textArea.getValueBoxBase().getElement().hasClassName(CssName.MATERIALIZE_TEXTAREA));
        assertEquals(InputType.TEXT, textArea.getType());
    }

    @Override
    public void testValue() {
        // given
        MaterialTextArea textArea = getWidget();

        // when / then
        textArea.setValue("test");
        assertEquals("test", textArea.getValue());
    }

    public void testResizeRule() {
        // given
        MaterialTextArea textArea = getWidget();

        // when / then
        textArea.setResizeRule(MaterialTextArea.ResizeRule.AUTO);
        assertEquals(MaterialTextArea.ResizeRule.AUTO, textArea.getResizeRule());
        textArea.setResizeRule(MaterialTextArea.ResizeRule.FOCUS);
        assertEquals(MaterialTextArea.ResizeRule.FOCUS, textArea.getResizeRule());
        textArea.setResizeRule(MaterialTextArea.ResizeRule.NONE);
        assertEquals(MaterialTextArea.ResizeRule.NONE, textArea.getResizeRule());
    }

    public void testValueChangeEvent() {
        // given
        MaterialTextArea textArea = getWidget();

        // when / then
        checkValueChangeEvent(textArea, "Value 1", "Value 2");
    }

    public void testFocus() {
        // given
        MaterialTextArea textArea = getWidget();

        // when / then
        checkResizeRule(textArea);
        textArea.removeFromParent();
        RootPanel.get().add(textArea);
        checkResizeRule(textArea);
    }

    protected void checkResizeRule(MaterialTextArea textArea) {
        textArea.setResizeRule(MaterialTextArea.ResizeRule.FOCUS);
        boolean[] fired = {false};
        textArea.addFocusHandler(focusEvent -> fired[0] = true);
        fireFocusEvent(textArea);
        assertTrue(fired[0]);
    }
}
