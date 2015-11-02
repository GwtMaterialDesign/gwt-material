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


import gwt.material.design.client.custom.helper.EnumHelper;

/**
 * @author Ben Dol
 */
public enum ShowOn implements CssType {
    SHOW_ON_SMALL("show-on-small"),
    SHOW_ON_LARGE("show-on-large"),
    SHOW_ON_MED("show-on-med"),
    SHOW_ON_MED_UP("show-on-medium-and-up"),
    SHOW_ON_MED_DOWN("hide-on-med-and-up");

    private final String cssClass;

    ShowOn(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static ShowOn fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, ShowOn.class, SHOW_ON_MED_UP);
    }
}