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
import gwt.material.design.client.constants.CollectionType;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Collections.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialCollectionTest extends MaterialWidgetTest<MaterialCollection> {

    @Override
    protected MaterialCollection createWidget() {
        return new MaterialCollection();
    }

    @Override
    public void testInitialClasses() {
        checkInitialClasses(CssName.COLLECTION);
    }

    public void testActive() {
        // given
        MaterialCollection collection = populateCollection(getWidget());

        // when / then
        // Set active index on one-base index
        collection.setActive(1);
        // We get the widget with one-base index which will 0
        assertEquals(collection.getActive(), collection.getWidget(0));
        assertTrue(collection.getChildren().get(0).getElement().hasClassName(CssName.ACTIVE));
        collection.setActive(2);
        assertEquals(collection.getActive(), collection.getWidget(1));
        assertFalse(collection.getChildren().get(0).getElement().hasClassName(CssName.ACTIVE));
        assertTrue(collection.getChildren().get(1).getElement().hasClassName(CssName.ACTIVE));
        // Clear the collection
        collection.clearActive();
        for (Widget w : collection.getChildren()) {
            assertFalse(w.getElement().hasClassName(CssName.ACTIVE));
        }
    }

    public void testHeader() {
        // given
        MaterialCollection collection = getWidget();

        // when / then
        collection.setHeader("Test");
        assertTrue(collection.getElement().hasClassName(CssName.WITH_HEADER));
        assertTrue(collection.getWidget(0).getElement().hasClassName(CssName.COLLECTION_HEADER));
    }

    public void testType() {
        MaterialCollectionItem item = new MaterialCollectionItem();
        item.setType(CollectionType.CHECKBOX);
        assertTrue(item.getElement().hasClassName(CollectionType.CHECKBOX.getCssName()));
        item.setType(CollectionType.AVATAR);
        assertFalse(item.getElement().hasClassName(CollectionType.CHECKBOX.getCssName()));
        assertTrue(item.getElement().hasClassName(CollectionType.AVATAR.getCssName()));
    }

    public void testSecondary() {
        MaterialCollectionItem item = new MaterialCollectionItem();
        MaterialCollectionSecondary secondary = new MaterialCollectionSecondary();
        item.add(secondary);
        assertNotNull(secondary);
        assertEquals(secondary, item.getWidget(0));
    }

    public void testDismissible() {
        MaterialCollectionItem item = new MaterialCollectionItem();
        item.setDismissible(true);
        assertTrue(item.isDismissible());
        assertTrue(item.getElement().hasClassName(CssName.DISMISSABLE));
        item.setDismissible(false);
        assertFalse(item.isDismissible());
        assertFalse(item.getElement().hasClassName(CssName.DISMISSABLE));
    }

    public void testCollectionItem() {
        // given
        MaterialCollection collection = getWidget();

        // when / then
        for (int i = 1; i <= 5; i++) {
            MaterialCollectionItem item = new MaterialCollectionItem();
            boolean[] linkClickHandler = {false};
            boolean[] clickHandler = {false};

            MaterialLink link = new MaterialLink("Link");
            link.addClickHandler(clickEvent -> linkClickHandler[0] = true);
            item.addClickHandler(clickEvent -> clickHandler[0] = true);
            item.add(link);
            collection.add(item);
            assertNotNull(item);

            fireClickEvent(item);
            fireClickEvent(link);
            assertTrue(clickHandler[0]);
            assertTrue(linkClickHandler[0]);
        }
        assertEquals(5, collection.getChildren().size());
    }

    public void testCheckBoxType() {
        // given / when
        MaterialCollection collection = populateCollection(getWidget());

        // then
        assertEquals(5, collection.getChildren().size());
    }

    protected MaterialCollection populateCollection(MaterialCollection collection) {
        for (int i = 1; i <= 5; i++) {
            MaterialCollectionItem item = new MaterialCollectionItem();

            boolean[] clickHandler = {false};
            item.addClickHandler(clickEvent -> clickHandler[0] = true);
            item.setType(CollectionType.CHECKBOX);
            assertTrue(item.getType() == CollectionType.CHECKBOX);
            MaterialCheckBox cb = new MaterialCheckBox();
            item.add(cb);
            boolean[] cbClickHandler = {false};
            cb.addClickHandler(clickEvent -> cbClickHandler[0] = true);
            fireClickEvent(cb);
            assertFalse(clickHandler[0]);

            collection.add(item);
            assertNotNull(item);
        }
        assertEquals(5, collection.getChildren().size());
        return collection;
    }
}
