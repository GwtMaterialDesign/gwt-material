package gwt.material.design.client.pwa.serviceworker;

public class ServiceEvent {

    private boolean preventDefault;
    private boolean stopPropagation;

    public ServiceEvent() {
    }

    public boolean isPreventDefault() {
        return preventDefault;
    }

    public void setPreventDefault(boolean preventDefault) {
        this.preventDefault = preventDefault;
    }

    public boolean isStopPropagation() {
        return stopPropagation;
    }

    public void setStopPropagation(boolean stopPropagation) {
        this.stopPropagation = stopPropagation;
    }
}
