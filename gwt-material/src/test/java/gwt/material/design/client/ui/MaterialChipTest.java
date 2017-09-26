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

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.base.AbstractValueWidgetTest;
import gwt.material.design.client.ui.html.Span;

/**
 * Test case for Chips.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialChipTest extends AbstractValueWidgetTest<MaterialChip> {

    @Override
    protected MaterialChip createWidget() {
        return new MaterialChip();
    }

    public void testLetter() {
        // given
        MaterialChip chip = getWidget();

        // when / then
        chip.setLetter("A");
        assertNotNull(chip.getWidget(0));
        assertTrue(chip.getWidget(0) instanceof Span);
        Span letter = (Span) chip.getWidget(0);
        assertEquals("A", chip.getLetter());
        assertEquals(letter.getText(), chip.getLetter());
        chip.setLetterBackgroundColor(Color.AMBER);
        assertTrue(letter.getElement().hasClassName(Color.AMBER.getCssName()));
        assertEquals(Color.AMBER, letter.getBackgroundColor());
        chip.setLetterColor(Color.AMBER);
        assertTrue(letter.getElement().hasClassName(Color.AMBER.getCssName()));
        assertEquals(Color.AMBER, letter.getTextColor());
    }

    public void testCloseHandler() {
        // given
        MaterialChip chip = getWidget();

        // when / then
        boolean[] closeHandler = new boolean[]{false};
        chip.addCloseHandler(closeEvent -> closeHandler[0] = true);
        chip.close();
        assertFalse(chip.isAttached());
        assertFalse(closeHandler[0]);
    }
}
