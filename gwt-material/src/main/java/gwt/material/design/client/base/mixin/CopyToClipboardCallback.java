package gwt.material.design.client.base.mixin;

import gwt.material.design.client.base.HasCopyToClipboard;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.MaterialIcon;

public interface CopyToClipboardCallback<T extends MaterialWidget & HasCopyToClipboard> {

    void call(T widget, MaterialIcon clipboardIcon, String text);
}
