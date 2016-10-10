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

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import gwt.material.design.client.base.error.BasicEditorError;

import java.util.ArrayList;
import java.util.List;

/**
 * Common validator code.
 *
 * @param <T> the generic type
 * @author Steven Jardine
 */
public abstract class AbstractValidator<T> implements Validator<T> {

    private String invalidMessageOverride = null;

    private String messageKey;

    private ValidatorMessageMixin messageMixin = GWT.create(ValidatorMessageMixin.class);

    private Object[] messageValueArgs;

    /**
     * Constructor. This overrides all validation message handling. Use this constructor for field specific
     * custom validation messages.
     *
     * @param invalidMessageOverride the invalid message override
     */
    public AbstractValidator(String invalidMessageOverride) {
        this(null, new Object[0]);
        assert invalidMessageOverride != null;
        this.invalidMessageOverride = invalidMessageOverride;
    }

    /**
     * Constructor. Looks up the message using the messageKey and replacing arguments with messageValueArgs.
     *
     * @param messageKey       the message key
     * @param messageValueArgs the message value args
     */
    public AbstractValidator(String messageKey, Object[] messageValueArgs) {
        this.messageKey = messageKey;
        this.messageValueArgs = messageValueArgs;
        assert this.messageValueArgs != null;
    }

    /**
     * Creates the error list.
     *
     * @param editor     the editor
     * @param value      the value
     * @param messageKey the message key
     * @return the list
     */
    public List<EditorError> createErrorList(Editor<T> editor, T value, String messageKey) {
        List<EditorError> result = new ArrayList<>();
        result.add(new BasicEditorError(editor, value, getInvalidMessage(messageKey)));
        return result;
    }

    /**
     * Gets the invalid message.
     *
     * @param key the key
     * @return the invalid message
     */
    public String getInvalidMessage(String key) {
        return invalidMessageOverride == null ? messageMixin.lookup(key, messageValueArgs) : MessageFormat.format(
                invalidMessageOverride, messageValueArgs);
    }

    /**
     * Checks if is valid.
     *
     * @param value the value
     * @return true, if is valid
     */
    public abstract boolean isValid(T value);

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<EditorError> validate(Editor<T> editor, T value) {
        List<EditorError> result = new ArrayList<>();
        if (!isValid(value)) {
            result.add(new BasicEditorError(editor, value, getInvalidMessage(messageKey)));
        }
        return result;
    }

}
