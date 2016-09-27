package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasEnabled;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.client.ui.dto.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MaterialListValueBoxTest extends MaterialWidgetTest {

    @Test
    public void testListValueBox() {
        MaterialListValueBox<User> valueBox = new MaterialListValueBox<>();
        checkWidget(valueBox);
        checkValues(valueBox);
        checkProperties(valueBox);
        MaterialListBox listBox = new MaterialListBox();
        checkValueAsListBox(listBox);
    }

    public <T extends MaterialListValueBox<User>> void checkValues(T widget) {
        List<User> users = new ArrayList<>();
        // Add Item test
        for (int i = 1; i <= 5; i++) {
            User user = new User("user" + i, "User " + i);
            users.add(user);
            widget.addItem(user);
        }

        for (int i = 0; i <=4; i++) {
            assertNotNull(widget.getValue(i));
        }

        // Set selected item test
        widget.setSelectedIndex(0);
        assertEquals(widget.getSelectedIndex(), 0);
        assertNotNull(widget.getSelectedValue());
        assertEquals(users.get(0), widget.getSelectedValue());

        // Remove item test
        widget.removeItem(0);
        users.remove(0);
        assertEquals(widget.getItemCount(), users.size());
        assertEquals(users.get(0), widget.getSelectedValue());

        // Insert item test
        User inserted = new User("inserted", "Inserted");
        widget.insertItem(inserted, 1);
        assertNotNull(inserted);
        assertEquals(widget.getValue(1), inserted);

        // Clear test
        widget.clear();
        assertNull(widget.getSelectedValue());
        assertEquals(widget.getItemCount(), 0);
    }

    public <T extends MaterialListValueBox<String>> void checkValueAsListBox(T widget) {
        List<String> users = new ArrayList<>();
        // Add Item test
        for (int i = 1; i <= 5; i++) {
            users.add("Item" + i);
        }

        for (int i = 0; i <=4; i++) {
            assertNotNull(widget.getValue(i));
        }

        // Set selected item test
        widget.setSelectedIndex(0);
        assertEquals(widget.getSelectedIndex(), 0);
        assertNotNull(widget.getSelectedValue());
        assertEquals(users.get(0), widget.getSelectedValue());

        // Remove item test
        widget.removeItem(0);
        users.remove(0);
        assertEquals(widget.getItemCount(), users.size());
        assertEquals(users.get(0), widget.getSelectedValue());

        // Insert item test
        String inserted = new String("inserted");
        widget.insertItem(inserted, 1);
        assertNotNull(inserted);
        assertEquals(widget.getValue(1), inserted);

        // Clear test
        widget.clear();
        assertNull(widget.getSelectedValue());
        assertEquals(widget.getItemCount(), 0);
    }

    public <T extends MaterialListValueBox<User>> void checkProperties(T widget) {
        widget.setOld(true);
        assertTrue(widget.isOld());
        widget.setOld(false);
        assertFalse(widget.isOld());
        widget.setMultipleSelect(true);
        assertTrue(widget.isMultipleSelect());
    }

    @Override
    public <T extends MaterialWidget & HasEnabled> void checkEnabled(T widget) {
        assertTrue(widget instanceof MaterialListValueBox);
        final Element element = ((MaterialListValueBox<User>)widget).getListBox().getElement();
        assertFalse(element.hasAttribute(CssName.DISABLED));
        widget.setEnabled(true);
        assertFalse(element.hasAttribute(CssName.DISABLED));
        assertEquals(widget.isEnabled(), true);
        widget.setEnabled(false);
        assertTrue(element.hasAttribute(CssName.DISABLED));
    }
}
