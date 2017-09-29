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

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.html.ListItem;

/**
 * Test case for Nav Brand.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialNavSectionTest extends MaterialWidgetTest<MaterialNavSection> {

    @Override
    protected MaterialNavSection createWidget() {
        return new MaterialNavSection();
    }

    public void testPositions() {
        // given
        MaterialNavSection navSection = getWidget();

        // when / then
        navSection.setPosition(Position.LEFT);
        assertTrue(navSection.getElement().hasClassName(Position.LEFT.getCssName()));

        navSection.setPosition(Position.RIGHT);
        assertTrue(navSection.getElement().hasClassName(Position.RIGHT.getCssName()));
        assertFalse(navSection.getElement().hasClassName(Position.LEFT.getCssName()));
    }

    public void testAddLinks() {
        // given
        MaterialNavSection navSection = getWidget();
        MaterialNavBar navBar = MaterialNavBarTest.constructAndAttach();

        // when / then
        for (int i = 1; i <= 5; i++) {
            navSection.add(new MaterialLink("Nav Link " + i));
        }
        for (Widget w : navSection.getChildren()) {
            assertTrue(w instanceof ListItem);
            ListItem item = (ListItem) w;
            assertTrue(item.getWidget(0) instanceof MaterialLink);
        }
        navBar.add(navSection);
        assertEquals(5, navSection.getChildren().size());
    }

    public void testSelectionEvent() {
        MaterialNavSection widget = new MaterialNavSection();

        final boolean[] isSelectionEventFired = {false};
        widget.addSelectionHandler(selectionEvent -> isSelectionEventFired[0] = true);
        widget.fireEvent(new GwtEvent<SelectionHandler<?>>() {
            @Override
            public Type<SelectionHandler<?>> getAssociatedType() {
                return SelectionEvent.getType();
            }

            @Override
            protected void dispatch(SelectionHandler eventHandler) {
                eventHandler.onSelection(null);
            }
        });

        assertTrue(isSelectionEventFired[0]);
    }
}
