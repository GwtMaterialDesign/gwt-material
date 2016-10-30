/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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

/**
 * @author chriswjones
 */
public enum Flex {
    INITIAL(0, 1, "auto"),
    NONE(0, 0, "auto"),
    AUTO(1, 1, "auto"),
    ONE(1, 1, "0px"),
    TWO(2, 1, "0px"),
    THREE(3, 1, "0px"),
    FOUR(4, 1, "0px"),
    FIVE(5, 1, "0px"),
    SIX(6, 1, "0px"),
    SEVEN(7, 1, "0px"),
    EIGHT(8, 1, "0px"),
    NINE(9, 1, "0px"),
    TEN(10, 1, "0px"),
    ELEVEN(11, 1, "0px"),
    TWELVE(12, 1, "0px");

    private final int grow;
    private final int shrink;
    private final String basis;

    Flex(final int grow, final int shrink, final String basis) {
        this.grow = grow;
        this.shrink = shrink;
        this.basis = basis;
    }

    public int getGrow() {
        return grow;
    }

    public int getShrink() {
        return shrink;
    }

    public String getBasis() {
        return basis;
    }
}
