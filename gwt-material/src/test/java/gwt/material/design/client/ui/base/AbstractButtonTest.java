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
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.UIObject;
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
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setSize(ButtonSize.LARGE);
        assertEquals(ButtonSize.LARGE, widget.getSize());

        widget.setSize(ButtonSize.MEDIUM);
        assertEquals(ButtonSize.MEDIUM, widget.getSize());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setSize(ButtonSize.LARGE);
        assertTrue(element.hasClassName(ButtonSize.LARGE.getCssName()));
        widget.setSize(ButtonSize.MEDIUM);
        assertTrue(element.hasClassName(ButtonSize.MEDIUM.getCssName()));
        assertFalse(element.hasClassName(ButtonSize.LARGE.getCssName()));
    }

    public void testType() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setType(ButtonType.FLAT);
        assertEquals(ButtonType.FLAT, widget.getType());

        widget.setType(ButtonType.FLOATING);
        assertEquals(ButtonType.FLOATING, widget.getType());

        widget.setType(ButtonType.RAISED);
        assertEquals(ButtonType.RAISED, widget.getType());

        widget.setType(ButtonType.OUTLINED);
        assertEquals(ButtonType.OUTLINED, widget.getType());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setType(ButtonType.FLAT);
        assertTrue(element.hasClassName(ButtonType.FLAT.getCssName()));

        widget.setType(ButtonType.FLOATING);
        assertTrue(element.hasClassName(ButtonType.FLOATING.getCssName()));

        widget.setType(ButtonType.RAISED);
        assertTrue(element.hasClassName(ButtonType.RAISED.getCssName()));

        widget.setType(ButtonType.OUTLINED);
        assertTrue(element.hasClassName(ButtonType.OUTLINED.getCssName()));
    }

    public void testActivates() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setActivates("test");
        assertEquals("test", widget.getActivates());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setActivates("test");
        assertTrue(element.hasAttribute("data-activates"));
        assertEquals("test", element.getAttribute("data-activates"));
    }

    public void testHref() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setHref("href-test");
        assertEquals("href-test", widget.getHref());
        widget.setTarget("_blank");
        assertEquals("_blank", widget.getTarget());

        // Standard
        // given
        attachWidget();

        // when / then
        final Element element = widget.getElement();
        widget.setHref("href-test");
        assertTrue(element.hasAttribute("href"));
        assertEquals("href-test", element.getAttribute("href"));
        widget.setTarget("_blank");
        assertTrue(element.hasAttribute("target"));
        assertEquals("_blank", element.getAttribute("target"));
    }

    public void testText() {
        // UiBinder
        // given
        T widget = getWidget(false);

        // when / then
        widget.setText("test");
        assertEquals("test", widget.getText());

        // Standard
        // given
        attachWidget();

        // when / then
        widget.setText("test1");
        assertEquals("test1", widget.getText());
    }

    @Override
    protected <H extends UIObject & HasEnabled> void checkEnabled(HasEnabled widget, H target) {
        super.checkEnabled(widget, target);

        widget.setEnabled(false);
        assertTrue(target.getElement().hasAttribute("onclick"));
        assertEquals("return false", target.getElement().getAttribute("onclick"));

        widget.setEnabled(true);
        assertFalse(target.getElement().hasAttribute("onclick"));
    }
}
