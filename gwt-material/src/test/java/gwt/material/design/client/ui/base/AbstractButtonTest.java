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
package gwt.material.design.client.ui.base;

import com.google.gwt.dom.client.Element;
import gwt.material.design.client.base.AbstractButton;
import gwt.material.design.client.base.HasActivates;
import gwt.material.design.client.base.HasHref;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.constants.ButtonSize;
import gwt.material.design.client.constants.ButtonType;

/**
 * Test case for Abstract button
 *
 * @author kevzlou7979
 */
public abstract class AbstractButtonTest extends MaterialWidgetTest {

    public void checkBaseButton(AbstractButton button) {
        checkWidget(button);
        checkHref(button);
        checkActivates(button);
        checkType(button);
        checkSize(button);
        checkText(button);
    }

    protected <T extends AbstractButton> void checkSize(T widget) {
        final Element element = widget.getElement();
        widget.setSize(ButtonSize.LARGE);
        assertTrue(element.hasClassName(ButtonSize.LARGE.getCssName()));
        widget.setSize(ButtonSize.MEDIUM);
        assertTrue(element.hasClassName(ButtonSize.MEDIUM.getCssName()));
        assertFalse(element.hasClassName(ButtonSize.LARGE.getCssName()));
        assertEquals(widget.getSize(), ButtonSize.MEDIUM);
    }

    protected <T extends AbstractButton & HasType<ButtonType>> void checkType(T widget) {
        final Element element = widget.getElement();
        widget.setType(ButtonType.FLAT);
        assertTrue(element.hasClassName(ButtonType.FLAT.getCssName()));
        widget.setType(ButtonType.FLOATING);
        assertTrue(element.hasClassName(ButtonType.FLOATING.getCssName()));
        assertFalse(element.hasClassName(ButtonType.FLAT.getCssName()));
        assertEquals(widget.getType(), ButtonType.FLOATING);
    }

    protected <T extends AbstractButton & HasActivates> void checkActivates(T widget) {
        final Element element = widget.getElement();
        widget.setActivates("test");
        assertTrue(element.hasAttribute("data-activates"));
        assertEquals(element.getAttribute("data-activates"), "test");
        assertEquals(widget.getActivates(), "test");
    }

    protected <T extends AbstractButton & HasHref> void checkHref(T widget) {
        final Element element = widget.getElement();
        widget.setHref("href-test");
        assertTrue(element.hasAttribute("href"));
        assertEquals(element.getAttribute("href"), "href-test");
        assertEquals(widget.getHref(), "href-test");
        widget.setTarget("_blank");
        assertTrue(element.hasAttribute("target"));
        assertEquals(element.getAttribute("target"), "_blank");
        assertEquals(widget.getTarget(), "_blank");
    }

    protected <T extends AbstractButton> void checkText(T widget) {
        widget.setText("test");
        assertEquals(widget.getText(), "test");
    }
}
