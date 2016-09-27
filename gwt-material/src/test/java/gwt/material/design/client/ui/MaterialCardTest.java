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

import gwt.material.design.client.constants.Axis;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import org.junit.Test;

public class MaterialCardTest extends MaterialWidgetTest {

    @Test
    public void testCard() {
        MaterialCard card = new MaterialCard();
        checkAxis(card);
        checkStructure(card);
        checkWidget(card);
    }

    protected <T extends MaterialCard> void checkAxis(T widget) {
        widget.setAxis(Axis.HORIZONTAL);
        assertTrue(widget.getElement().hasClassName(Axis.HORIZONTAL.getCssName()));
        assertEquals(widget.getAxis(), Axis.HORIZONTAL);

        widget.setAxis(Axis.VERTICAL);
        assertTrue(widget.getElement().hasClassName(Axis.VERTICAL.getCssName()));
        assertEquals(widget.getAxis(), Axis.VERTICAL);
    }

    protected <T extends MaterialCard> void checkStructure(T widget) {
        MaterialCardContent content = new MaterialCardContent();
        widget.add(content);

        MaterialCardTitle title = new MaterialCardTitle();
        widget.add(title);

        MaterialCardAction action = new MaterialCardAction();
        widget.add(action);

        assertEquals(widget.getChildren().size(), 3);
        assertNotNull(content);
        assertNotNull(title);
        assertNotNull(action);
        assertTrue(widget.getWidget(0) instanceof MaterialCardContent);
        assertTrue(widget.getWidget(1) instanceof MaterialCardTitle);
        assertTrue(widget.getWidget(2) instanceof MaterialCardAction);
    }
}
