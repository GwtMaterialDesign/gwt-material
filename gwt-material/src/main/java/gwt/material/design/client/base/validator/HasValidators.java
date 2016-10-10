/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2015 GwtBootstrap3
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

import gwt.material.design.client.base.validator.ValidationChangedEvent.HasValidationChangedHandlers;

/**
 * Should use when implementing classes with {@link Validator}s.
 *
 * @param <T> the generic type
 * @author Steven Jardine
 */
public interface HasValidators<T> extends HasValidationChangedHandlers {

    /**
     * Adds the validator.
     *
     * @param validator the validator
     */
    void addValidator(Validator<T> validator);

    /**
     * Gets the validate on blur.
     *
     * @return the validate on blur
     */
    boolean isValidateOnBlur();

    /**
     * Removes the validator.
     *
     * @param validator the validator
     * @return true, if successful
     */
    boolean removeValidator(Validator<T> validator);

    /**
     * Reset the form element to blank and clear error messages.
     */
    void reset();

    /**
     * Sets the validate on blur.
     *
     * @param validateOnBlur the new validate on blur
     */
    void setValidateOnBlur(boolean validateOnBlur);

    /**
     * The validators used to validate this object.
     *
     * @param validators the new validators
     */
    void setValidators(@SuppressWarnings("unchecked") Validator<T>... validators);

    /**
     * Validate the field's value using the supplied validators.
     *
     * @return true, if valid
     */
    boolean validate();

    /**
     * Validate the field's value using the supplied validators.
     *
     * @param show the error to the user.
     * @return true, if valid
     */
    boolean validate(boolean show);

}
