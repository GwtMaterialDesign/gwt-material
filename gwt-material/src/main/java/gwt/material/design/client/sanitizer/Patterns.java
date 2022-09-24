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
    public static final String UNICODE = "[\\u0030-\\u0039\\u0660-\\u0669\\u06F0-\\u06F9\\u07C0-\\u07C9\\u0966-\\u0969\\u096A-\\u096F\\u09e6-\\u09e9\\u09ea-\\u09ef\\u0a66-\\u0a69\\u0a6a-\\u0a6f\\u0ae6-\\u0ae9\\u0aea-\\u0aef\\u0b66-\\u0b69\\u0b6a-\\u0b6f\\u0be6-\\u0be9\\u0bea-\\u0bef\\u0c66-\\u0c69\\u0c6a-\\u0c6f\\u0ce6-\\u0ce9\\u0cea-\\u0cef\\u0d66-\\u0d69\\u0d6a-\\u0d6f\\u0de6-\\u0de9\\u0dea-\\u0def\\u0e50-\\u0e59\\u0ed0-\\u0ed9\\u0f20-\\u0f29\\u1040-\\u1049\\u1090-\\u1099\\u17e0-\\u17e9\\u1810-\\u1819\\u1946-\\u1949\\u194a-\\u194ff\\u19d0-\\u19d9\\u1a80-\\u1a99\\u1b50-\\u1b59\\u1bb0-\\u1bb9\\u1c40-\\u1c59\\ua620-\\ua629\\ua8d0-\\ua8d9\\ua900-\\ua909\\ua9d0-\\ua9d9\\ua9f0-\\ua9f9\\uaa50-\\uaa59\\uabf0-\\uabf9\\uff10-\\uff19\\u104a0-\\u104a9\\u10d30-\\u10d39\\u11066-\\u11069\\u1106a-\\u1106f\\u110f0-\\u110f9\\u11136-\\u11139\\u1113a-\\u1113f\\u111d0-\\u111d9\\u112f0-\\u112f9\\u11450-\\u11459\\u114d0-\\u114d9\\u11650-\\u11659\\u116C0-\\u116c9\\u11730-\\u11739\\u118e0-\\u118e9\\u11950-\\u11959\\u11c50-\\u11c59\\u11d50-\\u11d59\\u11da0-\\u11da9\\u16a60-\\u16a69\\u16ac0-\\u16ac9\\u16b50-\\u16b59\\u1d7ce\\u1d7cf\\u1d7d0-\\u1d7d9\\u1d7da-\\u1d7df\\u1d7e0-\\u1d7e9\\u1d7ea-\\u1d7ef\\u1d7f0-\\u1d7f9\\u1d7fa-\\u1d7ff\\u1e140-\\u1e149\\u1e2f0-\\u1e2f9\\u1e950-\\u1e959\\u1fbf0-\\u1fbf9]";
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
    public static final String ACCENTS = "[\\u0300-\\u036f]";
    public static final String SPECIAL_UNICODE_CHARACTERS="[\\u8868\\u30dd\\u3042\\u0041\\u9dd7\\u0153\\u00e9\\uff22\\u900d\\u00fc\\u00df\\u00aa\\u0105\\u00f1\\u4e02\\u3400\\u20000]";
    public static final String REGIONAL_INDICATOR_SYMBOLS = "[\\u1f1e6-\\u1f1ff]";
    public static final String UPSIDE_DOWN= "[\\u0250\\u0bbf\\u00a1]";

}
