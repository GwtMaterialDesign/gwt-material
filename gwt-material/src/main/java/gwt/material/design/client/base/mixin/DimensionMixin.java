/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasBorder;
import gwt.material.design.client.base.HasDimension;

/**
 * @author kevzlou7979
 */
public class DimensionMixin<T extends Widget & HasBorder> extends StylePropertyMixin<T> implements HasDimension {

    static final String MIN_HEIGHT = "minHeight";
    static final String MAX_HEIGHT = "maxHeight";
    static final String MIN_WIDTH = "minWidth";
    static final String MAX_WIDTH = "maxWidth";

    public DimensionMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void setMinHeight(String value) {
        setProperty(MIN_HEIGHT, value);
    }

    @Override
    public String getMinHeight() {
        return getProperty(MIN_HEIGHT);
    }

    @Override
    public void setMaxHeight(String value) {
        setProperty(MAX_HEIGHT, value);
    }

    @Override
    public String getMaxHeight() {
        return getProperty(MAX_HEIGHT);
    }

    @Override
    public void setMinWidth(String value) {
        setProperty(MIN_WIDTH, value);
    }

    @Override
    public String getMinWidth() {
        return getProperty(MIN_WIDTH);
    }

    @Override
    public void setMaxWidth(String value) {
        setProperty(MAX_WIDTH, value);
    }

    @Override
    public String getMaxWidth() {
        return getProperty(MAX_WIDTH);
    }
}
