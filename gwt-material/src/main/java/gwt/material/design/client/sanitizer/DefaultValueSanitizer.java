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

public class DefaultValueSanitizer extends AbstractValueSanitizer {

    public DefaultValueSanitizer() {
        super();

        reservedString(true);
        special(true);
        numeric(true);
        unicode(true);
        japanese(true);
        chinese(true);
        emoji(true);
        rtl(true);
        zalgo(true);
        ogham(true);
        superscriptAndSubscript(true);
        letterLikeSymbols(true);
        currencySymbols(true);
        /*arrowSymbols(true);*/
        numberForms(true);
        quotation(true);
        nonWhiteSpaceC0Controls(true);
        nonWhiteSpaceC1Controls(true);
        greek(true);
        whiteSpaceZsZlZp(true);
        accents(true);
        specialUnicodeCharacters(true);
        regionalIndicatorSymbols(true);
    }
}
