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

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;

import java.util.List;

/**
 * An input validator.
 *
 * @param <T> the type.
 * @author Steven Jardine
 */
public interface Validator<T> {

    /**
     * Represents the priority of a validator.
     */
    public static class Priority {

        /**
         * HIGHEST priority
         */
        public static final int HIGHEST = 0;

        /**
         * HIGH priority
         */
        public static final int HIGH = 25;

        /**
         * MEDIUM priority
         */
        public static final int MEDIUM = 50;

        /**
         * LOW priority
         */
        public static final int LOW = 75;

        /**
         * LOWEST priority
         */
        public static final int LOWEST = 100;

    }

    /**
     * Priority value for this validator. Lower the number, higher the priority.
     *
     * @return the priority.
     */
    int getPriority();

    /**
     * Validate the field.
     *
     * @param editor the {@link Editor}.
     * @param value  the value
     * @return the list
     */
    List<EditorError> validate(Editor<T> editor, T value);
}
