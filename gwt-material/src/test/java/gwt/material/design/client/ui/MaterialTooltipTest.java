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

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Tooltips
 *
 * @author kevzlou7979
 */
public class MaterialTooltipTest extends MaterialWidgetTest {

    public void init() {
        MaterialButton button = new MaterialButton();
        checkWidget(button);
        checkStructure(button);
    }

    public <T extends MaterialWidget> void checkStructure(T widget) {
        widget.setTooltip("Tooltip");
        assertNotNull(widget.getTooltip());
        assertEquals(widget.getTooltip(), "Tooltip");
        assertTrue(widget.getElement().hasAttribute("data-tooltip"));
        assertEquals(widget.getElement().getAttribute("data-tooltip"), "Tooltip");

        widget.setTooltipPosition(Position.RIGHT);
        assertEquals(widget.getTooltipPosition(), Position.RIGHT);
        assertTrue(widget.getElement().hasAttribute("data-position"));
        assertEquals(widget.getElement().getAttribute("data-position"), Position.RIGHT.getCssName());
        widget.setTooltipPosition(Position.LEFT);
        assertEquals(widget.getTooltipPosition(), Position.LEFT);
        assertTrue(widget.getElement().hasAttribute("data-position"));
        assertEquals(widget.getElement().getAttribute("data-position"), Position.LEFT.getCssName());
        widget.setTooltipPosition(Position.TOP);
        assertEquals(widget.getTooltipPosition(), Position.TOP);
        assertTrue(widget.getElement().hasAttribute("data-position"));
        assertEquals(widget.getElement().getAttribute("data-position"), Position.TOP.getCssName());
        widget.setTooltipPosition(Position.BOTTOM);
        assertEquals(widget.getTooltipPosition(), Position.BOTTOM);
        assertTrue(widget.getElement().hasAttribute("data-position"));
        assertEquals(widget.getElement().getAttribute("data-position"), Position.BOTTOM.getCssName());

        widget.setTooltipDelayMs(100);
        assertEquals(widget.getTooltipDelayMs(), 100);
        assertTrue(widget.getElement().hasAttribute("data-delay"));
        assertEquals(widget.getElement().getAttribute("data-delay"), String.valueOf(100));

        final String HTML = "<b>Tooltip</b>";
        widget.setTooltipHTML(HTML);
        assertEquals(widget.getTooltipHTML(), HTML);
    }

}
