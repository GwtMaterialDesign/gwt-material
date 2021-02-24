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
package gwt.material.design.client.constants;

import gwt.material.design.jquery.client.api.JQueryElement;

/**
 * Blur options configured by {@link OverlayOption#setBlur(Blur)}
 *
 * @author kevzlou7979
 */
public class Blur {

    private int value;
    private boolean fixedTarget;
    private JQueryElement[] targets;

    public Blur() {
    }

    public Blur(int value, JQueryElement... targets) {
        this.value = value;
        this.targets = targets;
    }

    /**
     * Will get the blur's value in pixels
     */
    public int getValue() {
        return value;
    }

    /**
     * Will set the blur's value in pixels
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Will get the target elements that had been blurred
     */
    public JQueryElement[] getTargets() {
        return targets;
    }

    /**
     * Will set the target elements to be blurred
     */
    public void setTargets(JQueryElement... targets) {
        this.targets = targets;
    }

    public boolean isFixedTarget() {
        return fixedTarget;
    }

    /**
     * Will apply a height of 100% to a fix target. This is a recommended fixed
     * fixed position element applying a blur.
     */
    public void setFixedTarget(boolean fixedTarget) {
        this.fixedTarget = fixedTarget;
    }
}
