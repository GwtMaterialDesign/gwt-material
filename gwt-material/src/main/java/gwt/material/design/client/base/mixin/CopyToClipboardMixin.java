package gwt.material.design.client.base.mixin;

import gwt.material.design.client.base.HasCopyToClipboard;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialValueBox;
import gwt.material.design.jquery.client.api.JQueryElement;
import gwt.material.design.jscore.client.api.Document;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class CopyToClipboardMixin<T extends MaterialValueBox & HasCopyToClipboard> extends AbstractMixin<T> implements HasCopyToClipboard {

    public static final String COPY_TO_CLIPBOARD = "copy-to-clipboard";

    protected T widget;
    protected boolean enabled;
    protected MaterialIcon icon = new MaterialIcon(IconType.CONTENT_COPY);

    public CopyToClipboardMixin(T widget) {
        super(widget);

        this.widget = widget;
        this.icon.addStyleName(COPY_TO_CLIPBOARD);
    }

    @Override
    public void setEnableCopyToClipboard(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            widget.add(icon);
            icon.addClickHandler(event -> copyToClipboard());
        } else {
            if (icon.isAttached()) {
                icon.removeFromParent();
            }
        }
    }

    protected void copyToClipboard() {
        Object value = widget.getValue();
        if (value != null) {
            JQueryElement element = $(widget.getValueBoxBase().getElement());
            if (widget.isReadOnly()) widget.setEnabled(true);
            element.select();
            Document.execCommand("copy");
            if (widget.isReadOnly()) widget.setEnabled(false);
        }
    }

    @Override
    public void setCopyToClipboardIcon(IconType iconType) {
        icon.setIconType(iconType);
    }

    @Override
    public boolean isEnableCopyToClipboard() {
        return enabled;
    }
}
