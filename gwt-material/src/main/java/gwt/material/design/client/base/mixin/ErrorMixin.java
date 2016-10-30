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

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasError;
import gwt.material.design.client.constants.CssName;

/**
 * @author Ben Dol
 */
public class ErrorMixin<T extends UIObject & HasError, H extends UIObject & HasText>
        extends AbstractMixin<T> implements HasError {

    private H textObject;
    private UIObject target;
    private UIObject lblPlaceholder;
    private String helperText;

    public ErrorMixin(final T widget) {
        this(widget, null);
    }

    public ErrorMixin(final T widget, final H textObject) {
        this(widget, textObject, widget);
    }

    public ErrorMixin(final T widget, final H textObject, UIObject target) {
        super(widget);

        this.textObject = textObject;
        this.target = target;
    }

    public ErrorMixin(final T widget, final H textObject, UIObject target, UIObject lblPlaceholder) {
        this(widget, textObject, target);
        this.lblPlaceholder = lblPlaceholder;
    }

    @Override
    public void setError(String error) {
        if (textObject != null) {
            textObject.setText(error);
            textObject.addStyleName(CssName.FIELD_ERROR_LABEL);
            textObject.removeStyleName(CssName.FIELD_HELPER_LABEL);
            textObject.removeStyleName(CssName.FIELD_SUCCESS_LABEL);
            textObject.setVisible(true);
        }
        if (target != null) {
            target.addStyleName(CssName.FIELD_ERROR);
            target.removeStyleName(CssName.FIELD_SUCCESS);
        }
        if (lblPlaceholder != null) {
            lblPlaceholder.removeStyleName("green-text");
            lblPlaceholder.addStyleName("red-text");
        }
    }

    @Override
    public void setSuccess(String success) {
        if (textObject != null) {
            textObject.setText(success);
            textObject.addStyleName(CssName.FIELD_SUCCESS_LABEL);
            textObject.removeStyleName(CssName.FIELD_HELPER_LABEL);
            textObject.removeStyleName(CssName.FIELD_ERROR_LABEL);
            textObject.setVisible(true);
        }
        if (target != null) {
            target.addStyleName(CssName.FIELD_SUCCESS);
            target.removeStyleName(CssName.FIELD_ERROR);
        }
        if (lblPlaceholder != null) {
            lblPlaceholder.removeStyleName("red-text");
            lblPlaceholder.addStyleName("green-text");
        }
    }

    @Override
    public void setHelperText(String helperText) {
        this.helperText = helperText;
        clearErrorOrSuccess();
    }

    @Override
    public void clearErrorOrSuccess() {
        if (textObject != null) {
            textObject.setText(helperText == null ? "" : helperText);
            if (helperText != null) {
                textObject.addStyleName(CssName.FIELD_HELPER_LABEL);
            } else {
                textObject.removeStyleName(CssName.FIELD_HELPER_LABEL);
            }
            textObject.removeStyleName(CssName.FIELD_ERROR_LABEL);
            textObject.removeStyleName(CssName.FIELD_SUCCESS_LABEL);
            textObject.setVisible(helperText != null);
        }
        if (target != null) {
            target.removeStyleName(CssName.FIELD_ERROR);
            target.removeStyleName(CssName.FIELD_SUCCESS);
        }
        if (lblPlaceholder != null) {
            lblPlaceholder.removeStyleName("red-text");
            lblPlaceholder.removeStyleName("green-text");
        }
    }
}
