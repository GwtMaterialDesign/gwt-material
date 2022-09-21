/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2022 GwtMaterialDesign
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
package gwt.material.design.client.sanitizer;

public class Patterns {

    public static final String STANDARD = "[\\u0000-\\u0255]";
    public static final String CHINESE = "[\\u2E80-\\u2FD5\\u3190-\\u319f\\u3400-\\u4DBF\\u4E00-\\u9FCC\\uF900-\\uFAAD]";
    public static final String SPECIAL = "(?=.*[-\\/:-@\\[-\\`{-~]{1,})";
    public static final String UNICODE = "[\\uE700-\\uE72E\\uE730\\uE731\\uE734\\uE735\\uE737-\\uE756]";
    public static final String JAPANESE = "[\\u3000-\\u303f\\u3040-\\u309f\\u30a0-\\u30ff\\uff00-\\uff9f\\u4e00-\\u9faf\\u3400-\\u4dbf\\uac00-\\ud7a3]";
    public static final String ZALGO = "[\\u0300-\\u0361\\u0316-\\u0362\\u0334-\\u0338\\u0363-\\u036f]";
    public static final String OGHAM = "[\\u1680-\\u169f]";
    public static final String SUPERSCRIPT_SUBSCRIPT = "[\\u2070-\\u209f]";
    public static final String CURRENCY_SYMBOLS = "[\\u20a0-\\u20cf]";
    public static final String LETTER_LIKE_SYMBOLS = "[\\u2100-\\u214f]";
    public static final String NUMBER_FORMS = "[\\u2150-\\u218f]";
    public static final String ARROW_SYMBOLS = "[\\u2190-\\u21ff\\u27f0-\\u27ff\\u2900-\\u297f\\u1f800-\\u1f8ff]";
    public static final String QUOTATION = "((?:'|\").*(?:'|\"))";
    public static final String NON_WHITESPACE_C0_CONTROLS ="[\\u0001-\\u0008\\u000e-\\u001f\\u007f]";
    public static final String NON_WHITESPACE_C1_CONTROLS = "[\\u0080-\\u0084\\u0086-\\u009f]";
    public static final String GREEK = "[\\u03b1-\\u03c9\\u0391-\\u03a9]";
    public static final String WHITESPACE_ZSZlZP = "[\\u0009\\u000b\\u000c\\u0085\\u200b\\u0000\\u000a\\u000d]";
}
