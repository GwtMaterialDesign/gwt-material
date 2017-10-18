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

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.Orientation;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Cards.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialCardTest extends MaterialWidgetTest<MaterialCard> {

    @Override
    protected MaterialCard createWidget() {
        return new MaterialCard();
    }

    @Override
    public void testInitialClasses() {
        checkInitialClasses(CssName.CARD);
    }

    public void testOrientation() {
        // UiBinder
        // given
        MaterialCard card = getWidget(false);

        // when / then
        card.setOrientation(Orientation.LANDSCAPE);
        assertEquals(Orientation.LANDSCAPE, card.getOrientation());

        card.setOrientation(Orientation.PORTRAIT);
        assertEquals(Orientation.PORTRAIT, card.getOrientation());

        // Standard
        // given
        attachWidget();

        // when / then
        card.setOrientation(Orientation.LANDSCAPE);
        assertTrue(card.getElement().hasClassName(Orientation.LANDSCAPE.getCssName()));

        card.setOrientation(Orientation.PORTRAIT);
        assertTrue(card.getElement().hasClassName(Orientation.PORTRAIT.getCssName()));
    }

    public void testStructure() {
        // given
        MaterialCard card = getWidget();

        // when / then
        MaterialCardContent content = new MaterialCardContent();
        card.add(content);

        MaterialCardTitle title = new MaterialCardTitle();
        card.add(title);

        MaterialCardAction action = new MaterialCardAction();
        card.add(action);

        MaterialCardReveal cardReveal = new MaterialCardReveal();
        card.add(cardReveal);

        MaterialCardImage cardImage = new MaterialCardImage();
        card.add(cardImage);

        assertEquals(5, card.getChildren().size());
        assertTrue(card.getWidget(0) instanceof MaterialCardContent);
        assertTrue(card.getWidget(1) instanceof MaterialCardTitle);
        assertTrue(card.getWidget(2) instanceof MaterialCardAction);
        assertTrue(card.getWidget(3) instanceof MaterialCardReveal);
        assertTrue(card.getWidget(4) instanceof MaterialCardImage);
        for (Widget w : card.getChildren()) {
            assertNotNull(w);
            assertTrue(w instanceof MaterialWidget);
        }
        card.clear();
        assertEquals(0, card.getChildren().size());
    }
}
