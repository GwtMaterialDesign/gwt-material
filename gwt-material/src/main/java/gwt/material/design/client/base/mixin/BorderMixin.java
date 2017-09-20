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

/**
 *  @author kevzlou7979
 */
public class BorderMixin<T extends Widget & HasBorder> extends StylePropertyMixin<T>  implements HasBorder {

    static final String BORDER = "border";
    static final String BORDER_LEFT = "borderLeft";
    static final String BORDER_RIGHT = "borderRight";
    static final String BORDER_TOP = "borderTop";
    static final String BORDER_BOTTOM = "borderBottom";
    static final String BORDER_RADIUS = "borderRadius";

    public BorderMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void setBorder(String value) {
        setProperty(BORDER, value);
    }

    @Override
    public String getBorder() {
        return getProperty(BORDER);
    }

    @Override
    public void setBorderLeft(String value) {
        setProperty(BORDER_LEFT, value);
    }

    @Override
    public String getBorderLeft() {
        return getProperty(BORDER_LEFT);
    }

    @Override
    public void setBorderRight(String value) {
        setProperty(BORDER_RIGHT, value);
    }

    @Override
    public String getBorderRight() {
        return getProperty(BORDER_RIGHT);
    }

    @Override
    public void setBorderTop(String value) {
        setProperty(BORDER_TOP, value);
    }

    @Override
    public String getBorderTop() {
        return getProperty(BORDER_TOP);
    }

    @Override
    public void setBorderBottom(String value) {
        setProperty(BORDER_BOTTOM, value);
    }

    @Override
    public String getBorderBottom() {
        return getProperty(BORDER_BOTTOM);
    }

    @Override
    public void setBorderRadius(String value) {
        setProperty(BORDER_RADIUS, value);
    }

    @Override
    public String getBorderRadius() {
        return getProperty(BORDER_RADIUS);
    }
}
