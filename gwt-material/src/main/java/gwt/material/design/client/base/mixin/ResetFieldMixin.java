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
package gwt.material.design.client.base.mixin;

import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.HasResetField;
import gwt.material.design.client.ui.MaterialValueBox;

/**
 * This mixin will perform resetting each {@link MaterialValueBox} inside the target {@link #content}.
 *
 * @author kevzlou7979@gmail.com
 */
public class ResetFieldMixin<C extends Widget> implements HasResetField {

    private C content;
    private boolean allowResettingFields = true;
    private boolean propagateToChildren = true;

    public ResetFieldMixin(C content) {
        assert content != null : "ResetFieldMixin content cannot be null";
        this.content = content;
    }

    @Override
    public void resetFields() {
        if (allowResettingFields) {
            if (content instanceof HasEnabled) {
                ((HasEnabled) content).setEnabled(true);
            }

            reset(content);
        }
    }

    protected void reset(Widget parent) {
        if (parent instanceof HasWidgets) {
            for (Widget child : (HasWidgets) parent) {
                if (child instanceof AbstractValueWidget) {
                    ((AbstractValueWidget) child).reset();
                }
                else if (propagateToChildren) {
                    reset(child);
                }
            }
        }
    }

    @Override
    public void setAllowResettingFields(boolean allowResettingFields) {
        this.allowResettingFields = allowResettingFields;
    }

    @Override
    public boolean isAllowResettingFields() {
        return allowResettingFields;
    }

    public boolean isPropagateToChildren() {
        return propagateToChildren;
    }

    public void setPropagateToChildren(boolean propagateToChildren) {
        this.propagateToChildren = propagateToChildren;
    }
}
