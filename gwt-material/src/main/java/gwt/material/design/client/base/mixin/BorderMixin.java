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
public class BorderMixin<T extends Widget & HasBorder> extends AbstractMixin<T>  implements HasBorder {

    public BorderMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void setBorder(String value) {
        setProperty("border", value);
    }

    @Override
    public String getBorder() {
        return getProperty("border");
    }

    @Override
    public void setBorderLeft(String value) {
        setProperty("borderLeft", value);
    }

    @Override
    public String getBorderLeft() {
        return getProperty("borderLeft");
    }

    @Override
    public void setBorderRight(String value) {
        setProperty("borderRight", value);
    }

    @Override
    public String getBorderRight() {
        return getProperty("borderRight");
    }

    @Override
    public void setBorderTop(String value) {
        setProperty("borderTop", value);
    }

    @Override
    public String getBorderTop() {
        return getProperty("borderTop");
    }

    @Override
    public void setBorderBottom(String value) {
        setProperty("borderBottom", value);
    }

    @Override
    public String getBorderBottom() {
        return getProperty("borderBottom");
    }

    protected String getProperty(String property) {
        return uiObject.getElement().getStyle().getProperty(property);
    }

    protected void setProperty(String property, String value) {
        assert property != null : "Property must be provided.";
        assert value != null : "Value must be provided";

        uiObject.getElement().getStyle().setProperty(property, value);
    }
}
