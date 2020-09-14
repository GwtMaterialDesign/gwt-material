package gwt.material.design.client.base.mixin;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 GwtBootstrap3
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

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.accessibility.AccessibilityControl;

/**
 * @author Sven Jacobs
 */
public class FocusableMixin<T extends UIObject & Focusable> extends AbstractMixin<T> implements Focusable {

    protected static final String FOCUS_VISIBLE = "focus-visible";

    public FocusableMixin(final T uiObject) {
        super(uiObject);
    }

    @Override
    public int getTabIndex() {
        return uiObject.getElement().getTabIndex();
    }

    @Override
    public void setTabIndex(final int index) {
        uiObject.getElement().setTabIndex(index);

        if (index > -1) {
            if (uiObject instanceof HasClickHandlers && uiObject instanceof Widget) {
                ((HasClickHandlers) uiObject).addClickHandler(event ->
                    AccessibilityControl.get().unregisterWidget((Widget) uiObject));
            }
        }
    }

    @Override
    public void setAccessKey(final char key) {
        final Element element = uiObject.getElement();
        final String accessKey = Character.toString(key);

        if (AnchorElement.is(element)) {
            AnchorElement.as(element).setAccessKey(accessKey);
        } else if (ButtonElement.is(element)) {
            ButtonElement.as(element).setAccessKey(accessKey);
        } else if (InputElement.is(element)) {
            InputElement.as(element).setAccessKey(accessKey);
        }
    }

    @Override
    public void setFocus(final boolean focused) {
        setFocus(focused, false);
    }

    public void setFocus(boolean focused, boolean appendFocusStyleName) {
        if (focused) {
            uiObject.getElement().focus();
            if (appendFocusStyleName) uiObject.addStyleName(FOCUS_VISIBLE);
        } else {
            uiObject.getElement().blur();
            if (appendFocusStyleName) uiObject.removeStyleName(FOCUS_VISIBLE);
        }
    }
}
