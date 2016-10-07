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

import gwt.material.design.client.base.HasTitle;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.html.Span;

public class TitleMixin<T extends MaterialWidget & HasTitle> extends AbstractMixin<T> implements HasTitle {

    private Span titleElem = new Span();
    private Span descElem = new Span();

    public TitleMixin(final T widget) {
        super(widget);
        widget.add(titleElem);
        widget.add(descElem);
    }

    @Override
    public void setTitle(String title) {
        titleElem.setText(title);
    }

    @Override
    public void setDescription(String description) {
        titleElem.setStyleName(CssName.TITLE);
        descElem.setText(description);
    }
}
