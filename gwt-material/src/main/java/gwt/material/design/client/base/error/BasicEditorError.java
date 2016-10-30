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
package gwt.material.design.client.base.error;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;

/**
 * Basic {@link EditorError} implementation.
 */
public class BasicEditorError implements EditorError {

    protected boolean consumed = false;

    protected Editor<?> editor = null;

    protected String message = null;

    protected Object value = null;

    /**
     * Create an new error.
     *
     * @param editor  the editor
     * @param value   the value
     * @param message the message
     */
    public BasicEditorError(Editor<?> editor, Object value, String message) {
        this.editor = editor;
        this.value = value;
        this.message = message;
    }

    @Override
    public String getAbsolutePath() {
        return null;
    }

    @Override
    public Editor<?> getEditor() {
        return editor;
    }

    @Override
    public String getMessage() {
        // TODO We may need to format the message using MessageFormat.
        return message;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public Object getUserData() {
        return null;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public boolean isConsumed() {
        return consumed;
    }

    @Override
    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }
}
