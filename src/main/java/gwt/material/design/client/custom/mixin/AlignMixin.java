package gwt.material.design.client.custom.mixin;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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

import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.constants.Alignment;
import gwt.material.design.client.custom.HasAlign;

/**
 * @author Ben Dol
 */
public class AlignMixin<T extends UIObject & HasAlign> extends AbstractMixin<T> implements HasAlign {

    private Alignment align;

    public AlignMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setAlign(Alignment align) {
        if(this.align != null) {
            uiObject.removeStyleName(this.align.getCssName() + "-align");
        }
        this.align = align;

        if(align != null) {
            uiObject.addStyleName(align.getCssName() + "-align");
        }
    }

    @Override
    public Alignment getAlign() {
        return align;
    }
}
