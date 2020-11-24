package gwt.material.design.client.base.mixin;

import gwt.material.design.client.base.CopyToClipboardLocale;
import gwt.material.design.client.base.HasCopyToClipboard;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.constants.Position;
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
    protected CopyToClipboardLocale locale = new CopyToClipboardLocale() {
    };
    protected CopyToClipboardCallback callback;

    public CopyToClipboardMixin(T widget) {
        super(widget);

        this.widget = widget;
        this.icon.addStyleName(COPY_TO_CLIPBOARD);
        this.widget.addAttachHandler(event -> {
            if (event.isAttached()) {
                setup();
            }
        });
    }

    @Override
    public void setEnableCopyToClipboard(boolean enabled) {
        this.enabled = enabled;
    }

    protected void setup() {
        if (enabled && widget.getType() != InputType.PASSWORD) {
            widget.add(icon);
            icon.addClickHandler(event -> copyToClipboard());
            icon.addMouseOutHandler(event -> updateTooltip(locale.CopyToClipboard()));
        } else {
            if (icon.isAttached()) {
                icon.removeFromParent();
            }
        }
    }

    protected void copyToClipboard() {
        String value = widget.getText();
        if (value != null) {
            JQueryElement element = $(widget.getValueBoxBase().getElement());
            if (widget.isReadOnly()) widget.setEnabled(true);
            element.select();
            Document.execCommand("copy");
            if (widget.isReadOnly()) widget.setEnabled(false);
            if (callback != null) callback.call(widget, icon, value);
            updateTooltip(locale.Copied() + " : " + value);
            $(icon.getElement()).trigger("mouseover", null);
        }
    }

    protected void updateTooltip(String tooltip) {
        icon.setTooltipPosition(Position.TOP);
        icon.setTooltipDelayMs(0);
        icon.setTooltip(tooltip);
    }

    @Override
    public void setCopyToClipboardCallback(CopyToClipboardCallback callback) {
        this.callback = callback;
    }

    @Override
    public void setCopyToClipboardLocale(CopyToClipboardLocale locale) {
        this.locale = locale;
    }

    @Override
    public void setCopyToClipboardIcon(MaterialIcon icon) {
        this.icon = icon;
    }

    @Override
    public MaterialIcon getCopyToClipboardIcon() {
        return icon;
    }

    @Override
    public boolean isEnableCopyToClipboard() {
        return enabled;
    }
}
