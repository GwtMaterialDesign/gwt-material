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

import com.google.gwt.editor.client.EditorError;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasError;

import java.util.List;

/**
 * @author Ben Dol
 */
public class ErrorMixin<T extends UIObject & HasError, H extends UIObject & HasText>
        extends AbstractMixin<T> implements HasError {

    private H textObject;
    private UIObject target;

    public ErrorMixin(final T widget, final H textObject, UIObject target) {
        super(widget);

        this.textObject = textObject;
        this.target = target;
    }

    @Override
    public void setError(String error) {
        textObject.setText(error);
        textObject.addStyleName("field-error-label");
        textObject.removeStyleName("field-success-label");
        if(target != null) {
            target.addStyleName("field-error");
            target.removeStyleName("field-success");
        }
        textObject.setVisible(true);
    }

    @Override
    public void setSuccess(String success) {
        textObject.setText(success);
        textObject.addStyleName("field-success-label");
        textObject.removeStyleName("field-error-label");
        if(target != null) {
            target.addStyleName("field-success");
            target.removeStyleName("field-error");
        }
        textObject.setVisible(true);
    }
}
