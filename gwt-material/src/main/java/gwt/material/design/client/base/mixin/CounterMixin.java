package gwt.material.design.client.base.mixin;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasCounter;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.MaterialTextBox;

/**
 * @author kevzlou7979
 */
public class CounterMixin<T extends UIObject & HasCounter> extends AbstractMixin<T> implements HasCounter {

    private final String LENGTH = "length";
    private int length = 0;

    public CounterMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setLength(int length) {
        this.length = length;
        Element e = null;

        if(uiObject instanceof MaterialTextBox){
            e = ((MaterialTextBox)uiObject).getTxtBox().getElement();

        }else if(uiObject instanceof MaterialTextArea){
            e = ((MaterialTextArea)uiObject).getTextArea().getElement();
        }

        if(e != null){
            e.setAttribute(LENGTH, String.valueOf(length));
            initCounter(e);
        }
    }

    @Override
    public int getLength() {
        return length;
    }

    /**
     * Initialize the character counter provided by the textbase elements
     * @param e - element to initialize the feature
     */
    private native void initCounter(Element e) /*-{
        $wnd.jQuery(document).ready(function() {
            $wnd.jQuery(e).characterCounter();
        });
    }-*/;
}
