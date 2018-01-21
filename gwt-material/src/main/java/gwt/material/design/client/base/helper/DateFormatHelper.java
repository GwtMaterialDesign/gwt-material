/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
package gwt.material.design.client.base.helper;

/**
 * Basic Date Format helper to support Java Based formats into <a href="http://amsul.ca/pickadate.js/date/#formats">Pickadate.js formats</a>
 * As of {@link gwt.material.design.client.ui.MaterialDatePicker} 2.0 we just supported the conversion of uppercase
 * formats to convert it to lower case for the basic support.
 *
 * @author kevzlou7979@gmail.com
 */
public class DateFormatHelper {

    static String DEFAULT_FORMAT = "d mmmm, yyyy";

    /**
     * Will auto format the given string to provide support for pickadate.js formats.
     */
    public static String format(String format) {

        if (format == null) {
            format = DEFAULT_FORMAT;
        } else {
            if (format.contains("M")) {
                format = format.replace("M", "m");
            }

            if (format.contains("Y")) {
                format = format.replace("Y", "y");
            }

            if (format.contains("D")) {
                format = format.replace("D", "d");
            }
        }
        return format;
    }
}
