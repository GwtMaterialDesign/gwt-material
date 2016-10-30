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

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Axis;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Cards
 *
 * @author kevzlou7979
 */
public class MaterialCardTest extends MaterialWidgetTest {

    public void init() {
        MaterialCard card = new MaterialCard();
        checkAxis(card);
        checkStructure(card);
        checkWidget(card);
    }

    protected <T extends MaterialCard> void checkAxis(T card) {
        card.setAxis(Axis.HORIZONTAL);
        assertTrue(card.getElement().hasClassName(Axis.HORIZONTAL.getCssName()));
        assertEquals(card.getAxis(), Axis.HORIZONTAL);

        card.setAxis(Axis.VERTICAL);
        assertTrue(card.getElement().hasClassName(Axis.VERTICAL.getCssName()));
        assertEquals(card.getAxis(), Axis.VERTICAL);
    }

    protected <T extends MaterialCard> void checkStructure(T card) {
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

        assertEquals(card.getChildren().size(), 5);
        assertTrue(card.getWidget(0) instanceof MaterialCardContent);
        assertTrue(card.getWidget(1) instanceof MaterialCardTitle);
        assertTrue(card.getWidget(2) instanceof MaterialCardAction);
        assertTrue(card.getWidget(3) instanceof MaterialCardReveal);
        assertTrue(card.getWidget(4) instanceof MaterialCardImage);
        for (Widget w : card.getChildren()) {
            assertNotNull(w);
            assertTrue(w instanceof MaterialWidget);
            MaterialWidget md = (MaterialWidget) w;
            checkWidget(md);
        }
        card.clear();
        assertEquals(card.getChildren().size(), 0);
    }
}
