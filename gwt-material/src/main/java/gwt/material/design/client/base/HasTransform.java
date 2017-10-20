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
 * The transform property applies a 2D or 3D transformation to an element.
 * This property allows you to rotate, scale, move, skew, etc., elements.
 *
 * @see <a href="https://www.w3schools.com/css/css3_3dtransforms.asp">CSS3 Transform</a>
 *
 * @author kevzlou7979
 */
public interface HasTransform {

    /**
     * Applies a 2D or 3D transformation to an element
     */
    void setTransform(String value);

    String getTransform();

    /**
     * Allows you to change the position on transformed elements
     */
    void setTransformOrigin(String value);

    String getTransformOrigin();

    /**
     * Specifies how nested elements are rendered in 3D space
     */
    void setTransformStyle(String value);

    String getTransformStyle();

    /**
     * Specifies the perspective on how 3D elements are viewed
     */
    void setPerspective(String value);

    String getPerspective();


    /**
     * Specifies the bottom position of 3D elements
     */
    void setPerspectiveOrigin(String value);

    String getPerspectiveOrigin();

    /**
     * Defines whether or not an element should be visible when not facing the screen
     */
    void setBackfaceVisibility(String value);

    String getBackfaceVisibility();
}
