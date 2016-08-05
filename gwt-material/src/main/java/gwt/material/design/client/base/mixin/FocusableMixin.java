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
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.UIObject;

/**
 * @author Sven Jacobs
 */
public class FocusableMixin<T extends UIObject & Focusable> extends AbstractMixin<T> implements Focusable {

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
        if (focused) {
            uiObject.getElement().focus();
        } else {
            uiObject.getElement().blur();
        }
    }
}
