package gwt.material.design.config;

public interface InputFieldConfig extends BaseWidgetConfig {

    @Key("GMD.InputField.Type")
    @DefaultStringValue("")
    String FieldType();

    @Key("GMD.InputField.StatusFieldType")
    @DefaultStringValue("")
    String StatusDisplayType();
}
