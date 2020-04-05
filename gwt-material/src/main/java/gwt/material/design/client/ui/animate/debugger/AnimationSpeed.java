/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2020 GwtMaterialDesign
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
package gwt.material.design.client.ui.animate.debugger;

public enum AnimationSpeed {

    SLOWEST(4),
    SLOWER(3),
    SLOW(2),
    NORMAL(1),
    FAST(0.75),
    FASTER(0.5),
    FASTEST(0.125);

    protected double value;

    AnimationSpeed(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
