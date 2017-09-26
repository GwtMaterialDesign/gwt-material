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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Timer;
import gwt.material.design.client.MaterialTestCase;

import static gwt.material.design.jquery.client.api.JQuery.$;

/**
 * Test case for Toasts.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialToastTest extends MaterialTestCase {

    public void testMultipleToasts() {
        for (int i = 1; i <= 5; i++) {
            MaterialToast.fireToast("test" + i);
        }
        Element toastContainer = $("body").find("#toast-container").asElement();
        assertNotNull(toastContainer);
        assertEquals(toastContainer.getChildCount(), 5);
        // Check each toasts
        for (int i = 0; i < 5; i++) {
            Element toastElement = (Element) toastContainer.getChild(i);
            assertEquals(toastElement.getInnerHTML(), "test" + (i + 1));
        }
        toastContainer.setInnerHTML("");
        assertEquals(toastContainer.getChildCount(), 0);
    }

    public void testToastWithCallback() {
        final boolean[] isCallbackFired = new boolean[1];
        new MaterialToast(() -> {
            isCallbackFired[0] = true;
        }).toast("callback", 1000);
        Timer t = new Timer() {
            @Override
            public void run() {
                assertTrue(isCallbackFired[0]);
            }
        };
        t.schedule(1000);
        Element toastContainer = $("body").find("#toast-container").asElement();
        assertNotNull(toastContainer);
        toastContainer.setInnerHTML("");
    }

    public void testToastWithStyling() {
        MaterialToast.fireToast("test", "rounded");
        Element toastContainer = $("body").find("#toast-container").asElement();
        assertNotNull(toastContainer);
        assertEquals(toastContainer.getChildCount(), 1);
        assertNotNull(toastContainer.getChild(0));
        assertTrue(toastContainer.getChild(0) instanceof Element);
        Element toastElement = (Element) toastContainer.getChild(0);
        assertTrue(toastElement.hasClassName("rounded"));
        toastContainer.setInnerHTML("");
    }

    public void testToastWithWidget() {
        MaterialLink link = new MaterialLink();
        new MaterialToast(link).toast("test");
        Element toastContainer = $("body").find("#toast-container").asElement();
        assertNotNull(toastContainer);
        assertEquals(toastContainer.getChildCount(), 1);
        assertNotNull(toastContainer.getChild(0));
        assertTrue(toastContainer.getChild(0) instanceof Element);
        Element toastElement = (Element) toastContainer.getChild(0);
        // Check the span text
        assertEquals($(toastElement.getChild(0)).text(), "test");
        // Check the added link to toast component
        assertEquals(link.getElement(), toastElement.getChild(1));
        toastContainer.setInnerHTML("");
    }

    public void testToastStructure() {
        MaterialToast.fireToast("test");
        Element toastContainer = $("body").find("#toast-container").asElement();
        assertNotNull(toastContainer);
        assertEquals(toastContainer.getChildCount(), 1);
        assertNotNull(toastContainer.getChild(0));
        assertTrue(toastContainer.getChild(0) instanceof Element);
        Element toastElement = (Element) toastContainer.getChild(0);
        assertEquals(toastElement.getInnerHTML(), "test");
        toastContainer.setInnerHTML("");
    }
}
