package gwt.material.design.client.constants;

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


import gwt.material.design.client.base.helper.EnumHelper;

/**
 * @author Ben Dol
 */
public enum HideOn implements CssType {
    HIDE_ON_SMALL("hide-on-small-only"),
    HIDE_ON_SMALL_DOWN("hide-on-small-and-down"),
    HIDE_ON_MED("hide-on-med-only"),
    HIDE_ON_MED_DOWN("hide-on-med-and-down"),
    HIDE_ON_MED_UP("hide-on-med-and-up"),
    HIDE_ON_LARGE("hide-on-large-only");

    private final String cssClass;

    HideOn(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static HideOn fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, HideOn.class, HIDE_ON_SMALL);
    }
}