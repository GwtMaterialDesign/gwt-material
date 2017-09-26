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
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.base.AbstractValueWidgetTest;

/**
 * Test case for ValueBox.
 *
 * @author kevzlou7979
 */
public abstract class MaterialValueBoxTest<T extends MaterialValueBox> extends AbstractValueWidgetTest<T> {

    public abstract void testValue();

    public void testErrorSuccess() {
        // given
        T widget = getWidget();

        // when / then
        checkErrorSuccess(widget, widget.getValueBoxBase());
    }

    public void testPlaceholder() {
        // given
        T widget = getWidget();

        // when / then
        widget.setPlaceholder("Placeholder");
        assertEquals("Placeholder", widget.getPlaceholder());
        widget.setPlaceholder("");
        assertEquals("", widget.getPlaceholder());
    }

    public void testIcon() {
        // given
        T widget = getWidget();

        // when / then
        Element iconElement = widget.getIcon().getElement();
        widget.setIconType(IconType.POLYMER);
        assertEquals(IconType.POLYMER.getCssName(), iconElement.getInnerHTML());
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

    public void testReadOnly() {
        // given
        T widget = getWidget();

        // when / then
        Element element = widget.getElement();
        widget.setReadOnly(true);
        assertTrue(element.hasAttribute("disabled"));
        assertTrue(element.hasClassName(CssName.READ_ONLY));
        assertTrue(widget.isReadOnly());
        widget.setReadOnly(false);
        assertFalse(element.hasAttribute("disabled"));
        assertFalse(element.hasClassName(CssName.READ_ONLY));
        assertFalse(widget.isReadOnly());

        widget.setToggleReadOnly(true);
        assertTrue(element.addClassName(CssName.READ_ONLY_TOGGLE));
        assertTrue(widget.isToggleReadOnly());
        widget.setToggleReadOnly(false);
        assertFalse(element.addClassName(CssName.READ_ONLY_TOGGLE));
        assertFalse(widget.isToggleReadOnly());
    }
}
