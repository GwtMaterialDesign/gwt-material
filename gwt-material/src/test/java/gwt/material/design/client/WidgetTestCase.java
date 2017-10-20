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
package gwt.material.design.client;

import com.google.gwt.event.logical.shared.HasAttachHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import org.junit.Ignore;

@Ignore
public abstract class WidgetTestCase<T extends Widget> extends MaterialTestCase {

    private T widget;

    protected abstract T createWidget();

    public T getWidget() {
        return getWidget(!neverAttach());
    }

    public T getWidget(boolean tryAttach) {
        if(widget == null) {
            widget = createWidget();
        }
        if(tryAttach && !widget.isAttached()) {
            attachWidget();
        }
        return widget;
    }

    protected void attachWidget() {
        if(!neverAttach()) {
            RootPanel.get().add(widget);

            if (unloadThenLoad()) {
                widget.removeFromParent();
                RootPanel.get().add(widget);
            }
        }
    }

    public boolean neverAttach() {
        return false;
    }

    public boolean unloadThenLoad() {
        return true;
    }

    public void destroyWidget() {
        if(widget != null && widget.isAttached()) {
            widget.removeFromParent();
        }
        widget = null;
    }

    public static <H extends HasValueChangeHandlers & HasValue & HasEnabled & HasAttachHandlers> void checkValueChangeEvent(
            H widget, Object value, Object secondValue) {
        assertNotSame(value, secondValue);
        // Widget must be enabled before firing the event
        widget.setEnabled(true);
        assertTrue(widget.isEnabled());
        // Ensure the widget is attached to the root panel
        assertTrue(widget.isAttached());
        // Register value change handler that listens when the widget
        // set the value
        final boolean[] isValueChanged = {false};
        widget.addValueChangeHandler(event -> isValueChanged[0] = true);
        // By default setValue(boolean) will not fire the value change event.
        widget.setValue(value);
        assertEquals(value, widget.getValue());
        // Expected result : false
        assertFalse(isValueChanged[0]);
        // Calling setValue(value, fireEvents) with fireEvents set to false
        widget.setValue(secondValue, false);
        // Expected result : secondValue
        assertEquals(secondValue, widget.getValue());
        // Expected result : false
        assertFalse(isValueChanged[0]);
        // Calling setValue(value, fireEvents) with fireEvents set to true
        widget.setValue(value, true);
        // Expected result : true
        assertTrue(isValueChanged[0]);
        // Expected result : value
        assertEquals(value, widget.getValue());
    }
}
