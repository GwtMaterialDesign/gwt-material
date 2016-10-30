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
 * A very simple message format implementation.
 *
 * @author Steven Jardine
 */
public class MessageFormat {

    /**
     * Format the message using the pattern and the arguments.
     *
     * @param pattern   the pattern in the format of "{1} this is a {2}"
     * @param arguments the arguments.
     * @return the formatted result.
     */
    public static String format(String pattern, Object... arguments) {
        String msg = pattern;
        if (arguments != null) {
            for (int index = 0; index < arguments.length; index++) {
                msg = msg.replaceAll("\\{" + (index + 1) + "\\}", String.valueOf(arguments[index]));
            }
        }
        return msg;
    }
}
