package gwt.material.design.client.ui;

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.CollectionType;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import org.junit.Test;

public class MaterialCollectionTest extends MaterialWidgetTest {

    @Test
    public void testCollection() {
        MaterialCollection collection = new MaterialCollection();
        checkWidget(collection);
        generateCollectionItem(collection);
        checkDismissible(collection);
        checkActive(collection);
        checkType(collection);
        checkSecondary(collection);
        checkHeader(collection);
    }

    protected <T extends MaterialCollection> void checkActive(T widget) {
        // Set active index on one-base index
        widget.setActive(1);
        // We get the widget with one-base index which will 0
        assertEquals(widget.getActive(), widget.getWidget(0));
        assertTrue(widget.getChildren().get(0).getElement().hasClassName(CssName.ACTIVE));
        widget.setActive(2);
        assertEquals(widget.getActive(), widget.getWidget(1));
        assertFalse(widget.getChildren().get(0).getElement().hasClassName(CssName.ACTIVE));
        assertTrue(widget.getChildren().get(1).getElement().hasClassName(CssName.ACTIVE));
        // Clear the collection
        widget.clearActive();
        for (Widget w : widget.getChildren()) {
            assertFalse(w.getElement().hasClassName(CssName.ACTIVE));
        }
    }

    protected <T extends MaterialCollection> void checkHeader(T widget) {
        widget.setHeader("Test");
        assertTrue(widget.getElement().hasClassName(CssName.WITH_HEADER));
        assertTrue(widget.getWidget(0).getElement().hasClassName(CssName.COLLECTION_HEADER));
    }

    protected <T extends MaterialCollection> void checkType(T widget) {
        MaterialCollectionItem item = generateFirstItem(widget);
        item.setType(CollectionType.CHECKBOX);
        assertTrue(item.getElement().hasClassName(CollectionType.CHECKBOX.getCssName()));
        item.setType(CollectionType.AVATAR);
        assertFalse(item.getElement().hasClassName(CollectionType.CHECKBOX.getCssName()));
        assertTrue(item.getElement().hasClassName(CollectionType.AVATAR.getCssName()));
    }

    protected <T extends MaterialCollection> void checkSecondary(T widget) {
        MaterialCollectionItem item = generateFirstItem(widget);
        MaterialCollectionSecondary secondary = new MaterialCollectionSecondary();
        item.add(secondary);
        assertNotNull(secondary);
        assertEquals(item.getWidget(0), secondary);
    }

    protected <T extends MaterialCollection> void checkDismissible(T widget) {
        MaterialCollectionItem item = generateFirstItem(widget);
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
