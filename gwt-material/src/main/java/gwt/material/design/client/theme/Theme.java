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

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public abstract class Theme extends HashMap<String, WidgetTheme> implements Comparable<Theme> {

    public abstract List<WidgetTheme> apply();

    public String getName() {
        return getClass().getName();
    }

    @Override
    public int compareTo(Theme theme) {
        return getPriority() - theme.getPriority();
    }

    /**
     * Priority of this themes loading. Last priority by default
     */
    public int getPriority() {
        return 999;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme that = (Theme) o;
        return Objects.equals(getName(), that.getName());
    }
}
