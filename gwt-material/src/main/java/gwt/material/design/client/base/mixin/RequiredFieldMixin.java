package gwt.material.design.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.HasRequiredField;

public class RequiredFieldMixin <T extends AbstractValueWidget & HasRequiredField, H extends UIObject>
        extends AbstractMixin<T> implements HasRequiredField {

    private String REQUIRED = "required";
    private H targetLabel;
    private boolean required;
    private ToggleStyleMixin<UIObject> toggleStyleMixin;

    public RequiredFieldMixin(T uiObject) {
        super(uiObject);
    }

    public RequiredFieldMixin(T uiObject, H targetLabel) {
        this(uiObject);

        this.targetLabel = targetLabel;
    }

    @Override
    public void setRequired(boolean required) {
        this.required = required;

        uiObject.setValidateOnBlur(true);
        uiObject.setAllowBlank(false);

        if (targetLabel != null) {
            getToggleStyleMixin().setOn(required);
        }
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public ToggleStyleMixin<UIObject> getToggleStyleMixin() {
        if (toggleStyleMixin == null) {
            toggleStyleMixin = new ToggleStyleMixin(targetLabel, REQUIRED);
        }
        return toggleStyleMixin;
    }
}
