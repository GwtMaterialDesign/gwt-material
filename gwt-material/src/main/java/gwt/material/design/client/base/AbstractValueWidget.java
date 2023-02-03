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
package gwt.material.design.client.base;

import com.google.gwt.dom.client.Element;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.error.ErrorHandler;
import gwt.material.design.client.base.error.ErrorHandlerType;
import gwt.material.design.client.base.error.HasErrorHandler;
import gwt.material.design.client.base.mixin.*;
import gwt.material.design.client.base.validator.BlankValidator;
import gwt.material.design.client.base.validator.HasValidators;
import gwt.material.design.client.base.validator.ValidationChangedEvent;
import gwt.material.design.client.base.validator.Validator;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.constants.StatusDisplayType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialValueBox;

import java.util.List;

public abstract class AbstractValueWidget<V> extends MaterialWidget implements HasResetField, HasValue<V>, LeafValueEditor<V>,
    HasEditorErrors<V>, HasErrorHandler, HasStatusText, HasValidators<V>, HasRequiredField, HasClearOnKeyUp, HasCopyCommand {

    private V initialValue;
    private boolean allowBlank = true;
    private boolean autoValidate;
    private BlankValidator<V> blankValidator;
    private ValidatorMixin<AbstractValueWidget<V>, V> validatorMixin;
    private StatusTextMixin<AbstractValueWidget, ?> statusTextMixin;
    private ErrorHandlerMixin<V> errorHandlerMixin;
    private RequiredFieldMixin<AbstractValueWidget, UIObject> requiredFieldMixin;
    private ResetFieldMixin<Widget> resetFieldMixin;
    private ClearOnKeyUpMixin<AbstractValueWidget, MaterialLabel> clearOnKeyUpMixin;
    private HandlerRegistration attachHandler, blurHandler;
    protected CopyCommandMixin<AbstractValueWidget> copyCommandMixin;

    public AbstractValueWidget(Element element) {
        super(element);
    }

    public AbstractValueWidget(Element element, String... initialClass) {
        super(element, initialClass);
    }

    @Override
    public void setValue(V value) {
        setValue(value, false);

        if (initialValue == null) {
            initialValue = value;
        }
    }

    @Override
    public void setValue(V value, boolean fireEvents) {
        if (fireEvents) {
            ValueChangeEvent.fire(this, value);
        }
    }

    public void setValue(V value, boolean fireEvents, boolean reload) {
        setValue(value, fireEvents);

        if (this instanceof HasReload) {
            if (reload) {
                ((HasReload) this).reload();
            }
        }
    }

    public V getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(V initialValue) {
        this.initialValue = initialValue;
    }

    @Override
    public void setErrorText(String errorText) {
        getStatusTextMixin().setErrorText(errorText);
    }

    @Override
    public void setSuccessText(String successText) {
        getStatusTextMixin().setSuccessText(successText);
    }

    @Override
    public void setHelperText(String helperText) {
        getStatusTextMixin().setHelperText(helperText);
    }

    @Override
    public void clearErrorText() {
        getStatusTextMixin().clearErrorText();
    }

    @Override
    public void clearHelperText() {
        getStatusTextMixin().clearHelperText();
    }

    @Override
    public void clearSuccessText() {
        getStatusTextMixin().clearSuccessText();
    }

    @Override
    public void clearStatusText() {
        getStatusTextMixin().clearStatusText();
    }

    @Override
    public boolean isErrorTextVisible() {
        return getStatusTextMixin().isErrorTextVisible();
    }

    @Override
    public boolean isHelperTextVisible() {
        return getStatusTextMixin().isHelperTextVisible();
    }

    @Override
    public boolean isSuccessTextVisible() {
        return getStatusTextMixin().isSuccessTextVisible();
    }

    @Override
    public void resetFields() {
        getResetFieldMixin().resetFields();
        getValidatorMixin().reset();
    }

    @Override
    public void setAllowResettingFields(boolean allowResettingFields) {
        getResetFieldMixin().setAllowResettingFields(allowResettingFields);
    }

    @Override
    public boolean isAllowResettingFields() {
        return getResetFieldMixin().isAllowResettingFields();
    }

    @Override
    public void setStatusDisplayType(StatusDisplayType displayType) {
        getStatusTextMixin().setStatusDisplayType(displayType);
    }

    @Override
    public StatusDisplayType getStatusDisplayType() {
        return getStatusTextMixin().getStatusDisplayType();
    }

    @Override
    public void setStatusShowByDefault(boolean showByDefault) {
        getStatusTextMixin().setStatusShowByDefault(showByDefault);
    }

    @Override
    public void updateStatusDisplay(StatusDisplayMixin.StatusType statusType) {
        getStatusTextMixin().updateStatusDisplay(statusType);
    }

    @Override
    public void showErrors(List<EditorError> errors) {
        getErrorHandlerMixin().showErrors(errors);
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return getErrorHandlerMixin().getErrorHandler();
    }

    @Override
    public void setErrorHandler(ErrorHandler errorHandler) {
        getErrorHandlerMixin().setErrorHandler(errorHandler);
    }

    @Override
    public ErrorHandlerType getErrorHandlerType() {
        return getErrorHandlerMixin().getErrorHandlerType();
    }

    @Override
    public void setErrorHandlerType(ErrorHandlerType errorHandlerType) {
        getErrorHandlerMixin().setErrorHandlerType(errorHandlerType);
    }

    @Override
    public void addValidator(Validator<V> validator) {
        getValidatorMixin().addValidator(validator);
    }

    @Override
    public boolean isValidateOnBlur() {
        return getValidatorMixin().isValidateOnBlur();
    }

    @Override
    public boolean removeValidator(Validator<V> validator) {
        return getValidatorMixin().removeValidator(validator);
    }

    @Override
    public void reset() {
        getValidatorMixin().reset();
    }

    @Override
    public void setValidateOnBlur(boolean validateOnBlur) {
        getValidatorMixin().setValidateOnBlur(validateOnBlur);
        setupBlurValidation();
    }

    @Override
    public void setValidators(@SuppressWarnings("unchecked") Validator<V>... validators) {
        getValidatorMixin().setValidators(validators);
    }

    @Override
    public boolean validate() {
        return getValidatorMixin().validate();
    }

    @Override
    public boolean validate(boolean show) {
        return getValidatorMixin().validate(show);
    }

    @Override
    public void setRequired(boolean required) {
        getRequiredFieldMixin().setRequired(required);
    }

    @Override
    public boolean isRequired() {
        return getRequiredFieldMixin().isRequired();
    }

    /**
     * Enable or disable the default blank validator.
     */
    public void setAllowBlank(boolean allowBlank) {
        this.allowBlank = allowBlank;

        // Setup the allow blank validation
        if (!allowBlank) {
            if (blankValidator == null) {
                blankValidator = createBlankValidator();
            }
            setupBlurValidation();
            addValidator(blankValidator);
        } else {
            removeValidator(blankValidator);
        }

    }

    /**
     * Will this widget allow blank values upon validation.
     */
    public boolean isAllowBlank() {
        return allowBlank;
    }

    /**
     * Hook for custom blank validators.
     *
     * @return the blank validatorMixin
     */
    protected BlankValidator<V> createBlankValidator() {
        return new BlankValidator<>();
    }

    protected void setupBlurValidation() {
        final AbstractValueWidget inputWidget = getValidatorMixin().getInputWidget();
        if (!inputWidget.isAttached()) {
            if (attachHandler == null) {
                attachHandler = inputWidget.addAttachHandler(event -> {
                    if (blurHandler == null) {
                        blurHandler = inputWidget.addBlurHandler(blurEvent -> {
                            validate(isValidateOnBlur());
                        });
                    }
                });
            }
        } else {
            if (blurHandler == null) {
                blurHandler = inputWidget.addBlurHandler(event -> validate(isValidateOnBlur()));
            }
        }
    }

    @Deprecated
    public boolean isAutoValidate() {
        return autoValidate;
    }

    /**
     * This method was deprecated and replaced by {@link #setValidateOnBlur(boolean)}
     */
    @Deprecated
    public void setAutoValidate(boolean autoValidate) {
        this.autoValidate = autoValidate;
        if (autoValidate) {
            if (isAttached()) {
                autoValidate();
            } else {
                registerHandler(addAttachHandler(event -> autoValidate()));
            }
        }
    }

    @Deprecated
    protected void autoValidate() {
        registerHandler(addBlurHandler(event -> validate()));
    }

    @Override
    public void setClearOnKeyUp(boolean value) {
        getClearOnKeyUpMixin().setClearOnKeyUp(value);
    }

    @Override
    public boolean isClearOnKeyUp() {
        return getClearOnKeyUpMixin().isClearOnKeyUp();
    }

    @Override
    public void setClearKeyCode(int keyCode) {
        getClearOnKeyUpMixin().setClearKeyCode(keyCode);
    }

    @Override
    public int getClearKeyCode() {
        return getClearOnKeyUpMixin().getClearKeyCode();
    }

    @Override
    public void setStatusDisplayPosition(Position position) {
        getStatusTextMixin().setStatusDisplayPosition(position);
    }

    @Override
    public void setCopyCommand(CopyCommand copyCommand) {
        getCopyCommandMixin().setCopyCommand(copyCommand);
    }

    @Override
    public CopyCommand getCopyCommand() {
        return getCopyCommandMixin().getCopyCommand();
    }

    @Override
    public void setCopyCommandCallback(CopyCommandCallback callback) {
        getCopyCommandMixin().setCopyCommandCallback(callback);
    }

    @Override
    public void setCopyCommandLocale(CopyCommandLocale locale) {
        getCopyCommandMixin().setCopyCommandLocale(locale);
    }

    @Override
    public void setCopyCommandIcon(MaterialIcon icon) {
        getCopyCommandMixin().setCopyCommandIcon(icon);
    }

    @Override
    public MaterialIcon getCopyCommandIcon() {
        return getCopyCommandMixin().getCopyCommandIcon();
    }

    @Override
    public HandlerRegistration addValidationChangedHandler(ValidationChangedEvent.ValidationChangedHandler handler) {
        return getValidatorMixin().addValidationChangedHandler(handler);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<V> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    protected ValidatorMixin<AbstractValueWidget<V>, V> getValidatorMixin() {
        if (validatorMixin == null) {
            validatorMixin = new ValidatorMixin<>(this, getErrorHandler());
        }
        return validatorMixin;
    }

    protected StatusTextMixin<AbstractValueWidget, ?> getStatusTextMixin() {
        if (statusTextMixin == null) {
            statusTextMixin = new StatusTextMixin<>(this);
        }
        return statusTextMixin;
    }

    protected ErrorHandlerMixin<V> getErrorHandlerMixin() {
        if (errorHandlerMixin == null) {
            errorHandlerMixin = new ErrorHandlerMixin<>(this);
        }
        return errorHandlerMixin;
    }

    protected RequiredFieldMixin<AbstractValueWidget, UIObject> getRequiredFieldMixin() {
        if (requiredFieldMixin == null) {
            requiredFieldMixin = new RequiredFieldMixin<>(this, getStatusTextMixin().getPlaceholder());
        }
        return requiredFieldMixin;
    }

    public ClearOnKeyUpMixin<AbstractValueWidget, MaterialLabel> getClearOnKeyUpMixin() {
        if (clearOnKeyUpMixin == null) {
            clearOnKeyUpMixin = new ClearOnKeyUpMixin(this, getStatusTextMixin().getPlaceholder());
        }
        return clearOnKeyUpMixin;
    }

    protected CopyCommandMixin<AbstractValueWidget> getCopyCommandMixin() {
        if (copyCommandMixin == null) {
            copyCommandMixin = new CopyCommandMixin(this);
        }
        return copyCommandMixin;
    }

    public ResetFieldMixin<Widget> getResetFieldMixin() {
        if (resetFieldMixin == null) {
            resetFieldMixin = new ResetFieldMixin<>(this);
        }
        return resetFieldMixin;
    }
}
