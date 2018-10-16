package gwt.material.design.client.config;

public interface InputFieldConfig extends WidgetConfig {

    @Key("GMD.InputField.Type")
    @DefaultStringValue("")
    String FieldType();

    @Key("GMD.InputField.StatusFieldType")
    @DefaultStringValue("")
    String StatusDisplayType();
}
