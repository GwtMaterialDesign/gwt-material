package gwt.material.design.client.base;

import com.google.gwt.user.client.ui.HasText;
import gwt.material.design.client.base.mixin.CopyToClipboardCallback;
import gwt.material.design.client.ui.MaterialIcon;

public interface HasCopyToClipboard {

    /**
     * Will append a copy to clipboard icon to the referenced widget.
     */
    void setEnableCopyToClipboard(boolean enable);

    void setCopyToClipboardCallback(CopyToClipboardCallback callback);

    void setCopyToClipboardLocale(CopyToClipboardLocale locale);

    void setCopyToClipboardIcon(MaterialIcon icon);

    MaterialIcon getCopyToClipboardIcon();

    boolean isEnableCopyToClipboard();
}
