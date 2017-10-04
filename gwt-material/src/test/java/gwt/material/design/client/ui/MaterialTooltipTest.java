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
import gwt.material.design.client.MaterialTestCase;
import gwt.material.design.client.constants.Position;

/**
 * Test case for Tooltips.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialTooltipTest extends MaterialTestCase {

    final static String TOOLTIP = "tooltip";
    final static int DELAY = 100;

    public void testStructure() {
        // given
        MaterialButton widget = new MaterialButton();
        RootPanel.get().add(widget);

        // when / then
        widget.setTooltip(TOOLTIP);
        assertNotNull(widget.getTooltip());
        assertEquals(TOOLTIP, widget.getTooltip());
        assertTrue(widget.getElement().hasAttribute("data-tooltip"));
        assertEquals(TOOLTIP, widget.getElement().getAttribute("data-tooltip"));

        widget.setTooltipPosition(Position.RIGHT);
        assertEquals(Position.RIGHT, widget.getTooltipPosition());
        assertTrue(widget.getElement().hasAttribute("data-position"));
        assertEquals(Position.RIGHT.getCssName(), widget.getElement().getAttribute("data-position"));
        widget.setTooltipPosition(Position.LEFT);
        assertEquals(Position.LEFT, widget.getTooltipPosition());
        assertTrue(widget.getElement().hasAttribute("data-position"));
        assertEquals(Position.LEFT.getCssName(), widget.getElement().getAttribute("data-position"));
        widget.setTooltipPosition(Position.TOP);
        assertEquals(Position.TOP, widget.getTooltipPosition());
        assertTrue(widget.getElement().hasAttribute("data-position"));
        assertEquals(Position.TOP.getCssName(), widget.getElement().getAttribute("data-position"));
        widget.setTooltipPosition(Position.BOTTOM);
        assertEquals(Position.BOTTOM, widget.getTooltipPosition());
        assertTrue(widget.getElement().hasAttribute("data-position"));
        assertEquals(Position.BOTTOM.getCssName(), widget.getElement().getAttribute("data-position"));

        widget.setTooltipDelayMs(DELAY);
        assertEquals(widget.getTooltipDelayMs(), DELAY);
        assertTrue(widget.getElement().hasAttribute("data-delay"));
        assertEquals(String.valueOf(DELAY), widget.getElement().getAttribute("data-delay"));

        // given
        final String HTML = "<b>Tooltip</b>";

        // when / then
        widget.setTooltipHTML(HTML);
        assertEquals(widget.getTooltipHTML(), HTML);
    }
}
