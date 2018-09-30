package gwt.material.design.client.base;

/**
 * See {@link gwt.material.design.client.base.mixin.RequiredFieldMixin}
 *
 * @author kevzlou7979
 */
public interface HasRequiredField {

    /**
     * Set the value widget to be a mandatory field by setting {@link AbstractValueWidget#allowBlank} to false, and
     * {@link AbstractValueWidget#autoValidate} will be enabled.
     */
    void setRequired(boolean value);

    /**
     * Check if the field is mandatory.
     */
    boolean isRequired();
}
