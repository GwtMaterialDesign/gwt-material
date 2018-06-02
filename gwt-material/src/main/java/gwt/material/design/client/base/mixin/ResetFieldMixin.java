package gwt.material.design.client.base.mixin;

import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.HasResetField;
import gwt.material.design.client.ui.MaterialValueBox;

/**
 * This mixin will perform resetting each {@link MaterialValueBox} inside the target {@link #content}.
 *
 * @author kevzlou7979@gmail.com
 */
public class ResetFieldMixin<C extends Widget> implements HasResetField {

    private C content;
    private boolean allowResettingFields;
    private boolean propagateToChildren = true;

    public ResetFieldMixin(C content) {
        assert content != null : "ResetFieldMixin content cannot be null";
        this.content = content;
    }

    @Override
    public void resetFields() {
        if (allowResettingFields) {
            if (content instanceof HasEnabled) {
                ((HasEnabled) content).setEnabled(true);
            }

            reset(content);
        }
    }

    protected void reset(Widget parent) {
        if (parent instanceof HasWidgets) {
            for (Widget child : (HasWidgets) parent) {
                if (child instanceof AbstractValueWidget) {
                    ((AbstractValueWidget) child).reset();
                }
                else if (propagateToChildren) {
                    reset(child);
                }
            }
        }
    }

    @Override
    public void setAllowResettingFields(boolean allowResettingFields) {
        this.allowResettingFields = allowResettingFields;
    }

    @Override
    public boolean isAllowResettingFields() {
        return allowResettingFields;
    }

    public boolean isPropagateToChildren() {
        return propagateToChildren;
    }

    public void setPropagateToChildren(boolean propagateToChildren) {
        this.propagateToChildren = propagateToChildren;
    }
}
