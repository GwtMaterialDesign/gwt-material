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
    public static final String SYMBOLS = "[\\u20a0-\\u20cf\\u2100-\\u214f\\u2190-\\u21ff\\u27f0-\\u27ff\\u2900-\\u297f\\u1f800-\\u1f8ff\\u27c0-\\u27ef\\u2980-\\u29ff\\u2600-\\u26ff\\u2b00-\\u2bff\\u1d100-\\u1d1ff" +
            "\\u2400-\\u243f\\u2460-\\u24ff\\u2500-\\u257f\\u2580-\\u259f\\u25a0-\\u25ff\\u1f780-\\u1f7ff\\u2700-\\u27bf\\u2800-\\u28ff\\u1f030-\\u1f09f\\u1f0a0-\\u1f0ff]";
    public static final String MATH_OPERATORS = "[\\u2200-\\u22ff\\u2a00-\\u2aff\\u2300-\\u23ff]";
    public static final String NUMBER_FORMS = "[\\u2150-\\u218f]";
    public static final String QUOTATION = "((?:'|\").*(?:'|\"))";
    public static final String NON_WHITESPACE_C0_CONTROLS ="[\\u0001-\\u0008\\u000e-\\u001f\\u007f]";
    public static final String NON_WHITESPACE_C1_CONTROLS = "[\\u0080-\\u0084\\u0086-\\u009f]";
}
