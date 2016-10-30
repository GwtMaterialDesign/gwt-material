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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.UIObject;

import static gwt.material.design.jquery.client.api.JQuery.$;

/**
 * @author Grant Slender
 */
public class HTMLMixin<T extends UIObject> extends TextMixin<T> implements HasHTML {

    public HTMLMixin(final T uiObject) {
        super(uiObject);
    }

    @Override
    public String getHTML() {
        return uiObject.getElement().getInnerHTML();
    }

    @Override
    public void setHTML(final String html) {
        try {
            uiObject.getElement().setInnerHTML(html);
        } catch (Exception e) {
            // try using jQuery.html() for handling IE, etc.
            setInnerHTML(uiObject.getElement(), html);
        }
    }

    protected void setInnerHTML(Element e, String html) {
        $(e).html(html);
    }
}
