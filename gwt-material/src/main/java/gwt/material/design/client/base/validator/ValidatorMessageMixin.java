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

/**
 * Mixin for looking up validation messages. This can be replaced with your own version by using a
 * "replace-with" statment in the gwt module file.
 * <p>
 * Example:
 * <p>
 * <pre>
 * {@code
 * <replace-with class="...CustomValidatorMessageMixin">
 *     <when-type-is class="org.gwtbootstrap3.client.ui.form.validator.ValidatorMessageMixin" />
 * </replace-with>
 * }
 * </pre>
 *
 * @author Steven Jardine
 */
public interface ValidatorMessageMixin {

    /**
     * Lookup the message using the supplied key.
     *
     * @param key the key.
     * @return the message associated with the given key.
     */
    String lookup(String key);

    /**
     * Lookup a message using the given key and replace the arguments in the given message with the supplied
     * values.
     * <p>
     * <pre>
     * {@code
     * Message:
     * {1} is a {2}
     *
     * Call:
     * lookup("key", "This", "test.");
     *
     * Returns:
     * This is a test.
     * }
     * </pre>
     *
     * @param key       the key
     * @param msgValues the values used in the message.
     * @return the message associated with the given key with the message values replaced.
     */
    String lookup(String key, Object[] msgValues);

}
