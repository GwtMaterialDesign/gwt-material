package gwt.material.design.client.ui.html;

import com.google.gwt.dom.client.Document;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.AttributeMixin;

public class IFrame extends MaterialWidget {

    enum Sandbox {
        ALL_RESTRICTIONS(""),
        ALLOW_FORMS("allow-forms"),
        ALLOW_POINTER_LOCK("allow-pointer-lock"),
        ALLOW_POPUPS("allow-popups"),
        ALLOW_SAME_ORIGIN("allow-same-origin"),
        ALLOW_SCRIPTS("allow-scripts"),
        ALLOW_TOP_NAVIGATION("allow-top-navigation");

        private String restriction;

        Sandbox(String restriction) {
            this.restriction = restriction;
        }

        public String getRestriction() {
            return restriction;
        }
    }

    private AttributeMixin<IFrame> attributeMixin;

    public IFrame() {
        super(Document.get().createIFrameElement());
        setSandBoxRestriction(Sandbox.ALL_RESTRICTIONS);
    }

    public void setSandBoxRestriction(Sandbox restriction) {
        getAttributeMixin().setAttribute(restriction.getRestriction());
    }

    public String getSandBoxRestriction() {
        return getAttributeMixin().getAttribute();
    }

    public AttributeMixin<IFrame> getAttributeMixin() {
        if (attributeMixin == null) {
            attributeMixin = new AttributeMixin<>(this, "sandbox");
        }
        return attributeMixin;
    }
}
