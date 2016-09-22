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
package gwt.material.design.client.base.mixin;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasCounter;
import gwt.material.design.client.ui.MaterialValueBox;

import static gwt.material.design.client.js.JsMaterialElement.$;

public class CounterMixin<T extends UIObject & HasCounter> extends AbstractMixin<T> implements HasCounter {

    private int length = 0;

    public CounterMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setLength(int length) {
        this.length = length;
        Element e = uiObject.getElement();

        if (uiObject instanceof MaterialValueBox) {
            e = ((MaterialValueBox) uiObject).asValueBoxBase().getElement();
        }

        if (e != null) {
            e.setAttribute("length", String.valueOf(length));
            initCounter(e);
        }
    }

    @Override
    public int getLength() {
        return length;
    }

    /**
     * Initialize the character counter provided by the textbase elements
     *
     * @param e - element to initialize the feature
     */
    protected void initCounter(Element e) {
        $(e).characterCounter();
    }
}
