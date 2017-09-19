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
package gwt.material.design.client.base;

/**
 * @author kevzlou7979
 */
public interface HasBorder {

    /**
     * Set the border style of material widget
     * @param value contains the Css border style, width color of an element. (e.i 1px solid black)
     */
    void setBorder(String value);

    String getBorder();

    /**
     * Sets the border style of material widget
     * @param value contains the Css border style, width color of an element. (e.i 1px solid black)
     */
    void setBorderLeft(String value);

    String getBorderLeft();

    /**
     * Sets the border right style of material widget
     * @param value contains the Css border style, width color of an element. (e.i 1px solid black)
     */
    void setBorderRight(String value);

    String getBorderRight();

    /**
     * Sets the border left style of material widget
     * @param value contains the Css border style, width color of an element. (e.i 1px solid black)
     */
    void setBorderTop(String value);

    String getBorderTop();

    /**
     * Sets the border bottom style of material widget
     * @param value contains the border style, width color of an element. (e.i 1px solid black)
     */
    void setBorderBottom(String value);

    String getBorderBottom();

    void setBorderRadius(String value);

    String getBorderRadius();
}
