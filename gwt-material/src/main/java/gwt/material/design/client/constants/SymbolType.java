/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2023 GwtMaterialDesign
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
package gwt.material.design.client.constants;

import gwt.material.design.client.base.helper.EnumHelper;

public enum SymbolType implements CssType {

    OUTLINED("material-symbols-outlined", "https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200&display=block"),
    ROUNDED("material-symbols-rounded", "https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200&display=block"),
    SHARP("material-symbols-sharp", "https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200&display=block");

    protected String name;
    protected String cssLink;

    SymbolType(String name, String cssLink) {
        this.name = name;
        this.cssLink = cssLink;
    }

    @Override
    public String getCssName() {
        return name;
    }

    public String getCssLink() {
        return cssLink;
    }

    public static SymbolType fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, SymbolType.class, OUTLINED);
    }
}
