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

import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.base.AbstractIconButtonTest;
import gwt.material.design.client.ui.html.Span;

/**
 * Test case for Chips
 *
 * @author kevzlou7979
 */
public class MaterialChipTest extends AbstractIconButtonTest {

    public void init() {
        MaterialChip chip = new MaterialChip();
        checkWidget(chip);
        checkIcon(chip);
        checkLetter(chip);
        checkCloseHandler(chip);
    }

    public <T extends MaterialChip> void checkLetter(T chip) {
        chip.setLetter("A");
        assertNotNull(chip.getWidget(0));
        assertTrue(chip.getWidget(0) instanceof Span);
        Span letter = (Span) chip.getWidget(0);
        assertEquals(chip.getLetter(), "A");
        assertEquals(chip.getLetter(), letter.getText());
        chip.setLetterBackgroundColor(Color.AMBER);
        assertTrue(letter.getElement().hasClassName(Color.AMBER.getCssName()));
        assertEquals(letter.getBackgroundColor(), Color.AMBER);
        chip.setLetterColor(Color.AMBER);
        assertTrue(letter.getElement().hasClassName(Color.AMBER.getCssName()));
        assertEquals(letter.getTextColor(), Color.AMBER);
    }

    @Override
    public <T extends MaterialWidget & HasCloseHandlers> void checkCloseHandler(T widget) {
        super.checkCloseHandler(widget);
        boolean[] closeHandler = new boolean[]{false};
        if (widget instanceof MaterialChip) {
            MaterialChip chip = (MaterialChip) widget;
            chip.addCloseHandler(closeEvent -> closeHandler[0] = true);
            chip.close();
            assertFalse(chip.isAttached());
            assertFalse(closeHandler[0]);
        }
    }
}
