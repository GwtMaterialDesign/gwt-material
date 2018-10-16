package gwt.material.design.config;

public interface HasStyleConfig<T extends BaseWidgetConfig> {

    void setupStyleConfig();

    T getConfig();
}
