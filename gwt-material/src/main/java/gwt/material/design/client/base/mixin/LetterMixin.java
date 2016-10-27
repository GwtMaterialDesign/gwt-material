/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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
package gwt.material.design.client.base.mixin;

import gwt.material.design.client.base.HasLetter;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.html.Span;

public class LetterMixin<T extends MaterialWidget & HasLetter> extends AbstractMixin<T> implements HasLetter {

    private Span span = new Span();

    public LetterMixin(final T widget) {
        super(widget);
        span.setStyleName(CssName.LETTER);
    }

    @Override
    public void setLetter(String letter) {
        span.setText(letter);
        uiObject.insert(span, 0);
    }

    @Override
    public String getLetter() {
        return span.getText();
    }

    @Override
    public void setLetterColor(Color letterColor) {
        span.setTextColor(letterColor);
    }

    @Override
    public void setLetterBackgroundColor(Color letterBackgroundColor) {
        span.setBackgroundColor(letterBackgroundColor);
    }

    public Span getSpan() {
        return span;
    }
}
