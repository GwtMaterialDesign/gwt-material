package gwt.material.design.client.base;

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

import com.google.gwt.dom.client.Element;
import gwt.material.design.client.constants.Type;

/**
 * Used to provide custom {@link Type} mixin elements.
 * @param <T>
 */
public class TypeWidget<T extends Type> extends ComplexWidget implements HasType<T> {

    public TypeWidget(Element element) {
        super(element);
    }

    @Override
    public void setType(T type) {
    }

    @Override
    public T getType() {
        return null;
    }
}