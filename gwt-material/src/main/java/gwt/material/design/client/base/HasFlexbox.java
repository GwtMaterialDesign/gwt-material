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
package gwt.material.design.client.base;

import com.google.gwt.dom.client.Style;
import gwt.material.design.client.base.mixin.FlexboxMixin;
import gwt.material.design.client.constants.*;

/**
 * Interface that determines the class implements Flexbox layout
 *
 * @author chriswjones
 * @see FlexboxMixin
 * @see MaterialWidget
 */
public interface HasFlexbox {
    void setGwtDisplay(Style.Display display);

    void setDisplay(Display display);

    void setVisible(boolean visible);

    void setFlexDirection(FlexDirection flexDirection);

    void setFlex(Flex flex);

    void setFlexGrow(Integer flexGrow);

    void setFlexShrink(Integer flexShrink);

    void setFlexBasis(String flexBasis);

    void setFlexOrder(Integer flexOrder);

    void setFlexWrap(FlexWrap flexWrap);

    void setFlexAlignContent(FlexAlignContent flexAlignContent);

    void setFlexAlignSelf(FlexAlignSelf flexAlignSelf);

    void setFlexAlignItems(FlexAlignItems flexAlignItems);

    void setFlexJustifyContent(FlexJustifyContent flexJustifyContent);
}
