package gwt.material.design.client.base;

import com.google.gwt.user.client.ui.Widget;

public interface HasActiveParent extends HasSelectables {

    /**
     * Providing the one-based index of the parent widget to mark as active.
     */
    void setActive(int index);

    /**
     * Providing the active value and one-based index of the parent widget to mark as active.
     */
    void setActive(int index, boolean value);

    /**
     * Get currently the active widget of it's parent
     */
    Widget getActive();
}
