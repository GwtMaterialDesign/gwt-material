package gwt.material.design.client.base;

import gwt.material.design.client.base.mixin.CopyToClipboardCallback;
import gwt.material.design.client.constants.IconType;

public interface HasCopyToClipboard {

    /**
     * Will append a copy to clipboard icon to the referenced widget.
     */
    void setEnableCopyToClipboard(boolean enable);

    void setCopyToClipboardCallback(CopyToClipboardCallback callback);

    void setCopyToClipboardIcon(IconType icon);

    boolean isEnableCopyToClipboard();
}
