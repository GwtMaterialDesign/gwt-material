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

import com.google.gwt.dom.client.Element;

/**
 * Css3 Transition property to be set {@link MaterialWidget#setTransition(TransitionConfig)}.
 *
 * @author kebzlou7979
 */
public class TransitionConfig {

    private Element target;
    private int duration;
    private int delay;
    private String property = "";
    private String timingFunction = "";

    public TransitionConfig() {}

    public TransitionConfig(Element target, int duration, int delay, String property, String timingFunction) {
        this.target = target;
        this.duration = duration;
        this.delay = delay;
        this.property = property;
        this.timingFunction = timingFunction;
    }

    public TransitionConfig(int duration, String property) {
        this.duration = duration;
        this.property = property;
    }

    public TransitionConfig(Element target, int duration, String property) {
        this.target = target;
        this.duration = duration;
        this.property = property;
    }

    public Element getTarget() {
        return target;
    }

    /**
     * Specifies the target element to apply the transition.
     */
    public void setTarget(Element target) {
        this.target = target;
    }

    public int getDuration() {
        return duration;
    }

    /**
     * Specifies how many seconds or milliseconds a transition effect takes to complete (In milliseconds)
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDelay() {
        return delay;
    }

    /**
     * Specifies a delay (in seconds) for the transition effect
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getProperty() {
        return property;
    }

    /**
     * Specifies the name of the CSS property the transition effect is for.
     */
    public void setProperty(String property) {
        this.property = property;
    }

    public String getTimingFunction() {
        return timingFunction;
    }

    /**
     * Specifies the speed curve of the transition effect (In milliseconds)
     */
    public void setTimingFunction(String timingFunction) {
        this.timingFunction = timingFunction;
    }
}
