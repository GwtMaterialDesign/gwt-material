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


import com.google.gwt.dom.client.Style;

/**
 * @author Ben Dol
 */
public interface HasInlineStyle extends HasColors, HasOpacity, HasFontSize {
    void setMargin(double margin);

    void setMarginTop(double margin);

    void setMarginLeft(double margin);

    void setMarginRight(double margin);

    void setMarginBottom(double margin);

    void setPadding(double padding);

    void setPaddingTop(double padding);

    void setPaddingLeft(double padding);

    void setPaddingRight(double padding);

    void setPaddingBottom(double padding);

    void setDisplay(Style.Display display);
}