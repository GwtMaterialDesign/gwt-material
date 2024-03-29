/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2022 GwtMaterialDesign
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

public interface HasTruncate {

    /**
     * If true the label inside this component will be truncated by ellipsis
     **/
    void setTruncate(boolean truncate);

    boolean isTruncate();

    /**
     * Will enable long texts to set the elements attribute when mouse overed the truncated text
     */
    void setEnableTruncateTitle(boolean value);

    boolean isEnableTruncateTitle();
}
