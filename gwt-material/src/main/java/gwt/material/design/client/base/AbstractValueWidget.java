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
import gwt.material.design.client.base.error.ErrorHandler;
import gwt.material.design.client.base.error.ErrorHandlerType;
import gwt.material.design.client.base.error.HasErrorHandler;
import gwt.material.design.client.base.mixin.ErrorHandlerMixin;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.base.mixin.ValidatorMixin;
import gwt.material.design.client.base.validator.BlankValidator;
import gwt.material.design.client.base.validator.HasValidators;
import gwt.material.design.client.base.validator.ValidationChangedEvent;
import gwt.material.design.client.base.validator.Validator;

import java.util.List;

//TODO: HasRawValue
public abstract class AbstractValueWidget<V> extends MaterialWidget implements HasValue<V>, LeafValueEditor<V>,
        HasEditorErrors<V>, HasErrorHandler, HasError, HasValidators<V> {

    private boolean allowBlank = true;
    private boolean autoValidate;
    private BlankValidator<V> blankValidator;
    private ValidatorMixin<AbstractValueWidget<V>, V> validatorMixin;
    private ErrorMixin<AbstractValueWidget, ?> errorMixin;
    private ErrorHandlerMixin<V> errorHandlerMixin;

    public AbstractValueWidget(Element element) {
        super(element);
    }

    public AbstractValueWidget(Element element, String... initialClass) {
        super(element, initialClass);
    }

    @Override
    public void setValue(V value) {
        setValue(value, false);
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
                ((HasReload)this).reload();
            }
        }
    }

    //TODO:
    //setSanitizer();

    @Override
    public void setError(String error) {
        getErrorMixin().setError(error);
    }

    @Override
    public void setSuccess(String success) {
        getErrorMixin().setSuccess(success);
    }

    @Override
    public void setHelperText(String helperText) {
        getErrorMixin().setHelperText(helperText);
    }

    @Override
    public void clearErrorOrSuccess() {
        getErrorMixin().clearErrorOrSuccess();
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
            registerHandler(inputWidget.addAttachHandler(attachEvent -> registerHandler(inputWidget.addBlurHandler(blurEvent -> validate(isValidateOnBlur())))));
        } else {
            registerHandler(inputWidget.addBlurHandler(blurEvent -> validate(isValidateOnBlur())));
        }
    }

    public boolean isAutoValidate() {
        return autoValidate;
    }

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

    protected void autoValidate() {
        registerHandler(addValueChangeHandler(event -> validate()));
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

    protected ErrorMixin<AbstractValueWidget, ?> getErrorMixin() {
        if (errorMixin == null) {
            errorMixin = new ErrorMixin<>(this);
        }
        return errorMixin;
    }

    protected ErrorHandlerMixin<V> getErrorHandlerMixin() {
        if (errorHandlerMixin == null) {
            errorHandlerMixin = new ErrorHandlerMixin<>(this);
        }
        return errorHandlerMixin;
    }
}
