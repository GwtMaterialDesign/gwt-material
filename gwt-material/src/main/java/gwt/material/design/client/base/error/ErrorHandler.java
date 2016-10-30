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

import com.google.gwt.editor.client.EditorError;

import java.util.List;

/**
 * Error handler.
 *
 * @author Steven Jardine
 */
public interface ErrorHandler {

    /**
     * Clean up the handler if necessary.
     */
    void cleanup();

    /**
     * Clear any errors.
     */
    void clearErrors();

    /**
     * Show the errors on the input screen.
     *
     * @param errors the errors to display.
     */
    void showErrors(List<EditorError> errors);
}
