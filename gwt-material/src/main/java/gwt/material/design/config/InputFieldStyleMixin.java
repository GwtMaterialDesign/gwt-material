package gwt.material.design.config;

import com.google.gwt.core.client.GWT;
import gwt.material.design.client.base.HasFieldTypes;
import gwt.material.design.client.base.HasStatusText;
import gwt.material.design.client.constants.FieldType;
import gwt.material.design.client.constants.StatusDisplayType;

public class InputFieldStyleMixin<T extends HasFieldTypes & HasStatusText> implements HasStyleConfig<InputFieldConfig> {

    private T hasFieldTypes;
    private InputFieldStyleMixin() {}

    public InputFieldStyleMixin(T hasFieldTypes) {
        this.hasFieldTypes = hasFieldTypes;
    }

    @Override
    public void setupStyleConfig() {
        if (getConfig() != null) {
            FieldType fieldType = FieldType.fromStyleName(getConfig().FieldType());
            if (fieldType != null) {
                hasFieldTypes.setFieldType(fieldType);
            }

            StatusDisplayType statusDisplayType = StatusDisplayType.fromStyleName(getConfig().StatusDisplayType());
            if (statusDisplayType != null) {
                hasFieldTypes.setStatusDisplayType(statusDisplayType);
            }
        }
    }

    @Override
    public InputFieldConfig getConfig() {
        return GWT.create(InputFieldConfig.class);
    }
}
