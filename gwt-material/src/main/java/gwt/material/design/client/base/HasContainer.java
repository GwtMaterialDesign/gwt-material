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
 * The container class is not strictly part of the grid but is important in laying out content.
 * It allows you to center your page content. The container class is set to ~70% of the window width.
 * It helps you center and contain your page content.
 *
 * @author kevzlou7979
 */
public interface HasContainer {

    /**
     * Enabled the container style feature inside the {@link gwt.material.design.client.ui.MaterialPanel}
     */
    void setContainerEnabled(boolean value);

    boolean isContainerEnabed();
}
