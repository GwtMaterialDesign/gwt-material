package gwt.material.design.client.base.mixin;

import gwt.material.design.client.base.CopyCommand;
import gwt.material.design.client.base.CopyCommandLocale;
import gwt.material.design.client.base.HasCopyCommand;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialValueBox;
import gwt.material.design.jquery.client.api.JQueryElement;
import gwt.material.design.jscore.client.api.Document;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class CopyCommandMixin<T extends MaterialValueBox & HasCopyCommand> extends AbstractMixin<T> implements HasCopyCommand {

    public static final String COPY_COMMAND = "copy-command";

    protected T widget;
    protected CopyCommand copyCommand = CopyCommand.OFF;
    protected MaterialIcon icon = new MaterialIcon(IconType.CONTENT_COPY);
    protected CopyCommandLocale locale = new CopyCommandLocale() {
    };
    protected CopyCommandCallback<T> callback;

    public CopyCommandMixin(T widget) {
        super(widget);

        this.widget = widget;
        this.icon.addStyleName(COPY_COMMAND);
        this.widget.addAttachHandler(event -> {
            if (event.isAttached()) setup();
        });
    }

    protected void setup() {
        if (copyCommand != null && copyCommand != CopyCommand.OFF && widget.getType() != InputType.PASSWORD) {
            widget.add(icon);
            icon.addStyleName(copyCommand.getName());
            icon.addClickHandler(event -> copyToClipboard());
            icon.addMouseOutHandler(event -> updateTooltip(locale.CopyToClipboard()));
            if (copyCommand == CopyCommand.ON_READONLY && !widget.isReadOnly()) icon.setVisible(false);
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
    public void setCopyCommand(CopyCommand copyCommand) {
        this.copyCommand = copyCommand;
    }

    @Override
    public CopyCommand getCopyCommand() {
        return copyCommand;
    }

    @Override
    public void setCopyCommandCallback(CopyCommandCallback callback) {
        this.callback = callback;
    }

    @Override
    public void setCopyCommandLocale(CopyCommandLocale locale) {
        this.locale = locale;
    }

    @Override
    public void setCopyCommandIcon(MaterialIcon icon) {
        this.icon = icon;
    }

    @Override
    public MaterialIcon getCopyCommandIcon() {
        return icon;
    }
}
