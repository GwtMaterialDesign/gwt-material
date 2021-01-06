/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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
import gwt.material.design.client.base.HasStatusText;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.constants.StatusDisplayType;

/**
 * @author Ben Dol
 */
public class StatusTextMixin<T extends UIObject & HasStatusText, H extends UIObject & HasText>
        extends AbstractMixin<T> implements HasStatusText {

    private H textObject;
    private UIObject target;
    private UIObject placeholder;
    private String helperText;
    private StatusDisplayMixin<T, H> statusDisplayMixin;

    public StatusTextMixin(final T widget) {
        this(widget, null);
    }

    public StatusTextMixin(final T widget, final H textObject) {
        this(widget, textObject, widget);
    }

    public StatusTextMixin(final T widget, final H textObject, UIObject target) {
        super(widget);

        this.textObject = textObject;
        this.target = target;
    }

    public StatusTextMixin(final T widget, final H textObject, UIObject target, UIObject placeholder) {
        this(widget, textObject, target);
        this.placeholder = placeholder;
    }

    @Override
    public void setErrorText(String errorText) {
        clearSuccessText();
        hideHelperText();
        updateStatusDisplay(StatusDisplayMixin.StatusType.ERROR);

        if (textObject != null) {
            textObject.setVisible(true);
            textObject.setText(errorText);
            textObject.addStyleName(CssName.FIELD_ERROR_LABEL);
        }

        if (target != null) {
            target.addStyleName(CssName.FIELD_ERROR);
        }

        if (placeholder != null) {
            placeholder.addStyleName("red-text");
            if (errorText != null && !errorText.isEmpty()) {
                placeholder.addStyleName(CssName.ACTIVE);
            }
        }
    }

    @Override
    public void setSuccessText(String successText) {
        clearErrorText();
        hideHelperText();
        updateStatusDisplay(StatusDisplayMixin.StatusType.SUCCESS);

        if (textObject != null) {
            textObject.setVisible(true);
            textObject.setText(successText);
            textObject.addStyleName(CssName.FIELD_SUCCESS_LABEL);
        }

        if (target != null) {
            target.addStyleName(CssName.FIELD_SUCCESS);
        }

        if (placeholder != null) {
            placeholder.addStyleName("green-text");
            if (successText != null && !successText.isEmpty()) {
                placeholder.addStyleName(CssName.ACTIVE);
            }
        }
    }


    @Override
    public void setHelperText(String helperText) {
        this.helperText = helperText;

        // Clear any other status states
        clearStatusText();

        if (helperText != null) {
            applyHelperText();
        } else {
            textObject.setText("");
            textObject.removeStyleName(CssName.FIELD_HELPER_LABEL);
            textObject.setVisible(false);
        }
    }

    @Override
    public void clearStatusText() {
        resetStatusDisplay();
        clearErrorText();
        clearSuccessText();
    }

    @Override
    public void clearErrorText() {
        if (textObject != null) {
            textObject.setText("");
            textObject.setVisible(false);
            textObject.removeStyleName(CssName.FIELD_ERROR_LABEL);
        }

        if (target != null) {
            target.removeStyleName(CssName.INVALID);
            target.removeStyleName(CssName.FIELD_ERROR);
        }

        if (placeholder != null) {
            placeholder.removeStyleName("red-text");
        }

        resetStatusDisplay();
        applyHelperText();
    }

    @Override
    public void clearSuccessText() {
        if (textObject != null) {
            textObject.setText("");
            textObject.setVisible(false);
            textObject.removeStyleName(CssName.FIELD_SUCCESS_LABEL);
        }

        if (target != null) {
            target.removeStyleName(CssName.VALID);
            target.removeStyleName(CssName.FIELD_SUCCESS);
        }

        if (placeholder != null) {
            placeholder.removeStyleName("green-text");
        }

        resetStatusDisplay();
        applyHelperText();
    }

    @Override
    public void clearHelperText() {
        helperText="";
        hideHelperText();
    }

    protected void hideHelperText() {
        if (textObject != null) {
            textObject.setText("");
            textObject.setVisible(false);
            textObject.removeStyleName(CssName.FIELD_HELPER_LABEL);
        }
    }

    public void applyHelperText() {
        if (textObject != null && helperText != null) {
            textObject.setText(helperText);
            textObject.addStyleName(CssName.FIELD_HELPER_LABEL);
            textObject.setVisible(helperText != null);
        }
    }

    @Override
    public boolean isErrorTextVisible() {
        return textObject != null && textObject.getStyleName().contains(CssName.FIELD_ERROR_LABEL)
                && textObject.isVisible();
    }

    @Override
    public boolean isHelperTextVisible() {
        return textObject != null && textObject.getStyleName().contains(CssName.FIELD_HELPER_LABEL)
                && textObject.isVisible();
    }

    @Override
    public boolean isSuccessTextVisible() {
        return textObject != null && textObject.getStyleName().contains(CssName.FIELD_SUCCESS_LABEL)
                && textObject.isVisible();
    }

    @Override
    public void setStatusDisplayType(StatusDisplayType displayType) {
        getStatusDisplayMixin().setStatusDisplayType(displayType);
    }

    public UIObject getPlaceholder() {
        return placeholder;
    }

    @Override
    public StatusDisplayType getStatusDisplayType() {
        return getStatusDisplayMixin().getStatusDisplayType();
    }

    @Override
    public void updateStatusDisplay(StatusDisplayMixin.StatusType statusType) {
        getStatusDisplayMixin().updateStatusDisplay(statusType);
    }

    @Override
    public void setStatusDisplayPosition(Position position) {
        getStatusDisplayMixin().setStatusDisplayPosition(position);
    }

    @Override
    public void setStatusShowByDefault(boolean showByDefault) {
        getStatusDisplayMixin().setStatusShowByDefault(showByDefault);
    }

    public void resetStatusDisplay() {
        getStatusDisplayMixin().resetStatusDisplay();
    }

    public StatusDisplayMixin<T, H> getStatusDisplayMixin() {
        if (statusDisplayMixin == null) {
            statusDisplayMixin = new StatusDisplayMixin<>(uiObject, textObject);
        }
        return statusDisplayMixin;
    }
}
