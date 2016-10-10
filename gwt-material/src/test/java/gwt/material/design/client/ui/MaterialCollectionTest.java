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
import gwt.material.design.client.constants.CollectionType;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Collections
 *
 * @author kevzlou7979
 */
public class MaterialCollectionTest extends MaterialWidgetTest {

    public void init() {
        MaterialCollection collection = new MaterialCollection();
        checkWidget(collection);
        generateCollectionItem(collection);
        checkDismissible(collection);
        checkActive(collection);
        checkType(collection);
        checkSecondary(collection);
        checkHeader(collection);
    }

    protected <T extends MaterialCollection> void checkActive(T collection) {
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

    protected <T extends MaterialCollection> void checkHeader(T collection) {
        collection.setHeader("Test");
        assertTrue(collection.getElement().hasClassName(CssName.WITH_HEADER));
        assertTrue(collection.getWidget(0).getElement().hasClassName(CssName.COLLECTION_HEADER));
    }

    protected <T extends MaterialCollection> void checkType(T collection) {
        MaterialCollectionItem item = generateFirstItem(collection);
        item.setType(CollectionType.CHECKBOX);
        assertTrue(item.getElement().hasClassName(CollectionType.CHECKBOX.getCssName()));
        item.setType(CollectionType.AVATAR);
        assertFalse(item.getElement().hasClassName(CollectionType.CHECKBOX.getCssName()));
        assertTrue(item.getElement().hasClassName(CollectionType.AVATAR.getCssName()));
    }

    protected <T extends MaterialCollection> void checkSecondary(T collection) {
        MaterialCollectionItem item = generateFirstItem(collection);
        MaterialCollectionSecondary secondary = new MaterialCollectionSecondary();
        item.add(secondary);
        assertNotNull(secondary);
        assertEquals(item.getWidget(0), secondary);
    }

    protected <T extends MaterialCollection> void checkDismissible(T collection) {
        MaterialCollectionItem item = generateFirstItem(collection);
        item.setDismissible(true);
        assertTrue(item.isDismissible());
        assertTrue(item.getElement().hasClassName(CssName.DISMISSABLE));
        item.setDismissible(false);
        assertFalse(item.isDismissible());
        assertFalse(item.getElement().hasClassName(CssName.DISMISSABLE));
    }

    protected MaterialCollectionItem generateFirstItem(MaterialCollection collection) {
        assertTrue(collection.getChildren().size() != 0);
        Widget w = collection.getWidget(0);
        assertTrue(w instanceof MaterialCollectionItem);
        return (MaterialCollectionItem) w;
    }

    protected void generateCollectionItem(MaterialCollection collection) {
        for (int i = 1; i <= 5; i++) {
            MaterialCollectionItem item = new MaterialCollectionItem();
            collection.add(item);
            assertNotNull(item);
        }
        assertEquals(collection.getChildren().size(), 5);
    }
}
