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

public enum Resolution implements Boundary {
    ALL_MOBILE(new WidthBoundary(0, 425)),
    ALL_LAPTOP(new WidthBoundary(769, 2560)),
    MOBILE_SMALL(new WidthBoundary(0, 320)),
    MOBILE_MEDIUM(new WidthBoundary(321, 375)),
    MOBILE_LARGE(new WidthBoundary(376, 425)),
    TABLET(new WidthBoundary(426, 768)),
    LAPTOP(new WidthBoundary(769, 1024)),
    LAPTOP_LARGE(new WidthBoundary(1025, 1440)),
    LAPTOP_4K(new WidthBoundary(1441, 2560));

    private final WidthBoundary boundary;

    Resolution(WidthBoundary boundary) {
        this.boundary = boundary;
    }

    public WidthBoundary getBoundary() {
        return boundary;
    }

    @Override
    public int getMin() {
        return boundary.getMin();
    }

    @Override
    public int getMax() {
        return boundary.getMax();
    }

    @Override
    public String asMediaQuery() {
        return boundary.asMediaQuery();
    }
}
