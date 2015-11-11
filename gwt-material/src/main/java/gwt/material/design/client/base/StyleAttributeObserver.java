package gwt.material.design.client.base;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class StyleAttributeObserver extends AttributeObserver {

    private String style;

    public StyleAttributeObserver(Widget widget, String style) {
        super(widget);
        this.style = style;
    }

    @Override
    protected JavaScriptObject createMutationObject() {
        return getMutationFunction("style", style);
    }

    public void observe(Element e) {
        super.observe(e, "style");
    }
}
