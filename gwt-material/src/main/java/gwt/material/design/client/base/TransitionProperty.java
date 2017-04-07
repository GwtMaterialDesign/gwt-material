package gwt.material.design.client.base;

import com.google.gwt.dom.client.Element;

/**
 * Css3 Transition property to be set {@link MaterialWidget#setTransition(TransitionProperty)}.
 *
 * @author kebzlou7979
 */
public class TransitionProperty {

    private Element target;
    private int duration;
    private int delay;
    private String property = "";
    private String timingFunction = "";

    public TransitionProperty() {}

    public TransitionProperty(Element target, int duration, int delay, String property, String timingFunction) {
        this.target = target;
        this.duration = duration;
        this.delay = delay;
        this.property = property;
        this.timingFunction = timingFunction;
    }

    public TransitionProperty(int duration, String property) {
        this.duration = duration;
        this.property = property;
    }

    public TransitionProperty(Element target, int duration, String property) {
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
