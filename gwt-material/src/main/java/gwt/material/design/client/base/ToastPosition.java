/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2021 GwtMaterialDesign
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

public enum ToastPosition {
    DEFAULT(""),
    TOP_LEFT("top-left"),
    TOP_RIGHT("top-right"),
    BOTTOM_LEFT("bottom-left"),
    BOTTOM_RIGHT("bottom-right");

    private String name;

    ToastPosition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
