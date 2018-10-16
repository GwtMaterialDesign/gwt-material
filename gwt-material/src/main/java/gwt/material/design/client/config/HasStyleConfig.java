package gwt.material.design.client.config;

public interface HasStyleConfig<T extends WidgetConfig> {

    void setupStyleConfig();

    T getConfig();
}
