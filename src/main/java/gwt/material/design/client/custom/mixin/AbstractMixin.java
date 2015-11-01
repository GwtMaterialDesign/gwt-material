package gwt.material.design.client.custom.mixin;

import com.google.gwt.user.client.ui.UIObject;

/**
 * @author Sven Jacobs
 */
abstract class AbstractMixin {

    UIObject uiObject;

    AbstractMixin(final UIObject uiObject) {
        this.uiObject = uiObject;
    }
}
