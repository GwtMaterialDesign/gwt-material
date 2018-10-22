/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
package gwt.material.design.client.theme;

import gwt.material.design.client.base.MaterialWidget;

import java.util.Objects;

public abstract class WidgetTheme<T extends MaterialWidget> {

    private Theme theme;
    private final Class<T> type;
    private final String selector;

    public WidgetTheme(Class<T> type) {
        this(type, type.getName());
    }

    public WidgetTheme(Class<T> type, String selector) {
        this.type = type;
        this.selector = selector;
    }

    public abstract T onWidgetLoad(T widget);

    public abstract T onWidgetUnload(T widget);

    public Class<T> getType() {
        return type;
    }

    public String getSelector() {
        return selector;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
