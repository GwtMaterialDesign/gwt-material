package gwt.material.design.client.base.mixin;

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


import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasInitialClass;

/**
 * @author kevzlou7979
 */
public class InitialClassMixin<T extends UIObject & HasInitialClass> extends AbstractMixin<T> implements HasInitialClass {

    private String[] initialClass;

    public InitialClassMixin(final T uiObject) {
        super(uiObject);
    }


    @Override
    public void setInitialClass(final String... initialClass) {
        this.initialClass = initialClass;
        ((Widget)uiObject).addAttachHandler(new AttachEvent.Handler() {
            @Override
            public void onAttachOrDetach(AttachEvent event) {

                for(String s : initialClass) {
                    if(initialClass != null && !s.isEmpty()) {
                        uiObject.removeStyleName(s);
                    }
                    if(event.isAttached()){
                        if(initialClass != null && !s.isEmpty()) {
                            uiObject.addStyleName(s);
                        }
                    }
                }
            }
        });
    }


    @Override
    public String[] getInitialClass() {
        return initialClass;
    }
}
