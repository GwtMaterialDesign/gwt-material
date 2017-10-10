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
package gwt.material.design.client.ui.base;

import com.google.gwt.dom.client.Element;
import gwt.material.design.client.base.AbstractButton;
import gwt.material.design.client.constants.ButtonSize;
import gwt.material.design.client.constants.ButtonType;
import org.junit.Ignore;

/**
 * Test case for Abstract button.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
@Ignore
public abstract class AbstractButtonTest<T extends AbstractButton> extends MaterialWidgetTest<T> {

    public void testSize() {
        // given
        T widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setSize(ButtonSize.LARGE);
        assertTrue(element.hasClassName(ButtonSize.LARGE.getCssName()));
        widget.setSize(ButtonSize.MEDIUM);
        assertTrue(element.hasClassName(ButtonSize.MEDIUM.getCssName()));
        assertFalse(element.hasClassName(ButtonSize.LARGE.getCssName()));
        assertEquals(ButtonSize.MEDIUM, widget.getSize());
    }

    public void testType() {
        // given
        T widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setType(ButtonType.FLAT);
        assertTrue(element.hasClassName(ButtonType.FLAT.getCssName()));
        widget.setType(ButtonType.FLOATING);
        assertTrue(element.hasClassName(ButtonType.FLOATING.getCssName()));
        assertFalse(element.hasClassName(ButtonType.FLAT.getCssName()));
        assertEquals(ButtonType.FLOATING, widget.getType());
    }

    public void testActivates() {
        // given
        T widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setActivates("test");
        assertTrue(element.hasAttribute("data-activates"));
        assertEquals("test", element.getAttribute("data-activates"));
        assertEquals("test", widget.getActivates());
    }

    public void testHref() {
        // given
        T widget = getWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setHref("href-test");
        assertTrue(element.hasAttribute("href"));
        assertEquals("href-test", element.getAttribute("href"));
        assertEquals("href-test", widget.getHref());
        widget.setTarget("_blank");
        assertTrue(element.hasAttribute("target"));
        assertEquals("_blank", element.getAttribute("target"));
        assertEquals("_blank", widget.getTarget());
    }

    public void testText() {
        // given
        T widget = getWidget();

        // when / then
        widget.setText("test");
        assertEquals("test", widget.getText());
    }
}
