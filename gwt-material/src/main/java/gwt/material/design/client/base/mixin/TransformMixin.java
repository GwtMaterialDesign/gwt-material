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
import gwt.material.design.client.base.HasTransform;

/**
 * @author kevzlou7979
 */
public class TransformMixin <T extends Widget & HasTransform> extends StylePropertyMixin<T>  implements HasTransform {

    static final String TRANSFORM = "transform";
    static final String TRANSFORM_ORIGIN = "transformOrigin";
    static final String TRANSFORM_STYLE = "transformStyle";
    static final String PERSPECTIVE = "perspective";
    static final String PERSPECTIVE_ORIGIN = "perspectiveOrigin";
    static final String BACKSPACE_VISIBILITY = "backspaceVisibility";

    public TransformMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void setTransform(String value) {
        setProperty(TRANSFORM, value);
    }

    @Override
    public String getTransform() {
        return getProperty(TRANSFORM);
    }

    @Override
    public void setTransformOrigin(String value) {
        setProperty(TRANSFORM_ORIGIN, value);
    }

    @Override
    public String getTransformOrigin() {
        return getProperty(TRANSFORM_ORIGIN);
    }

    @Override
    public void setTransformStyle(String value) {
        setProperty(TRANSFORM_STYLE, value);
    }

    @Override
    public String getTransformStyle() {
        return getProperty(TRANSFORM_STYLE);
    }

    @Override
    public void setPerspective(String value) {
        setProperty(PERSPECTIVE, value);
    }

    @Override
    public String getPerspective() {
        return getProperty(PERSPECTIVE);
    }

    @Override
    public void setPerspectiveOrigin(String value) {
        setProperty(PERSPECTIVE_ORIGIN, value);
    }

    @Override
    public String getPerspectiveOrigin() {
        return getProperty(PERSPECTIVE_ORIGIN);
    }

    @Override
    public void setBackfaceVisibility(String value) {
        setProperty(BACKSPACE_VISIBILITY, value);
    }

    @Override
    public String getBackfaceVisibility() {
        return getProperty(BACKSPACE_VISIBILITY);
    }
}
