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

public interface ValueSanitizer {

    boolean sanitize(String value) throws ValueSanitizerException;

    void enabled(boolean enabled);

    boolean isEnabled();

    ValueSanitizer reservedString(boolean sanitize);

    ValueSanitizer special(boolean sanitize);

    ValueSanitizer numeric(boolean sanitize);

    ValueSanitizer unicode(boolean sanitize);

    ValueSanitizer chinese(boolean sanitize);

    ValueSanitizer japanese(boolean sanitize);

    ValueSanitizer emoji(boolean sanitize);

    ValueSanitizer rtl(boolean sanitize);

    ValueSanitizer zalgo(boolean sanitize);

    ValueSanitizer ogham(boolean ogham);

    ValueSanitizer quotation(boolean sanitize);

    ValueSanitizer nonWhiteSpaceC0Controls(boolean sanitize);

    ValueSanitizer nonWhiteSpaceC1Controls(boolean sanitize);
}
