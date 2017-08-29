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
package gwt.material.design.client.base.viewport;

public enum Resolution {

    ALL_MOBILE(0, 425),
    ALL_LAPTOP(769, 2560),
    MOBILE_SMALL(0, 320),
    MOBILE_MEDIUM(321, 375),
    MOBILE_LARGE(376, 425),
    TABLET(426, 768),
    LAPTOP(769, 1024),
    LAPTOP_LARGE(1025, 1440),
    LAPTOP_4K(1441, 2560);

    private int minWidth;
    private int maxWidth;

    Resolution(int minWidth, int maxWidth) {
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public String asMediaQuery() {
        return "(min-width: " + minWidth + "px) and (max-width: " + maxWidth + "px)";
    }
}
