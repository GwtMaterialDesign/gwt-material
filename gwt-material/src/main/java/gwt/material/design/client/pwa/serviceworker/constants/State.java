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
package gwt.material.design.client.pwa.serviceworker.constants;

import gwt.material.design.client.base.helper.EnumHelper;
import gwt.material.design.client.constants.CssType;

public enum State implements CssType {
    INSTALLING("installing"),
    INSTALLED("installed"),
    ACTIVATING("activating"),
    ACTIVATED("activated"),
    REDUNDANT("redundant");

    private String stateName;

    State(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String getCssName() {
        return stateName;
    }

    public static State fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, State.class, null);
    }
}
