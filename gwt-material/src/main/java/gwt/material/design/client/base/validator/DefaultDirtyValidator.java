/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
package gwt.material.design.client.base.validator;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.IsPropagateToChildren;

import java.util.ArrayList;
import java.util.List;

public class DefaultDirtyValidator<C extends Widget> implements DirtyValidator, IsPropagateToChildren {

    private List<HandlerRegistration> registrations;
    private boolean allowDirty;
    private boolean propagateToChildren = true;
    private boolean dirty;
    private C content;

    public DefaultDirtyValidator(C content) {
        assert content != null : "DefaultDirtyValidator content cannot be null";
        this.content = content;
    }

    @Override
    public boolean isAllowDirtyValidation() {
        return allowDirty;
    }

    @Override
    public void setAllowDirtyValidation(boolean allowDirty) {
        this.allowDirty = allowDirty;

        if (allowDirty) {
            registrations = new ArrayList<>();
            detectDirtyFields(content);
        } else {
            if (registrations != null) {
                registrations.clear();
                registrations.forEach(HandlerRegistration::removeHandler);
                setDirty(false);
            }
        }
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    /**
     * Will check all components that extends to {@link HasValue} and
     * registers a {@link com.google.gwt.event.logical.shared.ValueChangeEvent}.
     * Once value has been changed then we mark that our content wrapping it is dirty.
     */
    protected void detectDirtyFields(Widget parent) {
        if (parent instanceof HasWidgets) {
            for (Widget widget : (HasWidgets) parent) {
                if (widget instanceof HasValue) {
                    HasValue valueWidget = (HasValue) widget;
                    registrations.add(valueWidget.addValueChangeHandler(event -> {
                        if (valueWidget.getValue() != null && ! valueWidget.getValue().equals("")) {
                            setDirty(true);
                        }
                    }));
                } else {
                    if (propagateToChildren) {
                        detectDirtyFields(widget);
                    }
                }
            }
        }
    }

    @Override
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    public String getDirtyMessage() {
        return "Are you sure you want to leave the page, some fields are dirty at the moment.";
    }

    @Override
    public boolean isPropagateToChildren() {
        return propagateToChildren;
    }

    @Override
    public void setPropagateToChildren(boolean propagateToChildren) {
        this.propagateToChildren = propagateToChildren;
    }
}
