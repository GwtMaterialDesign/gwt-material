package gwt.material.design.client.base.mixin;

import gwt.material.design.client.base.HasCopyCommand;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.MaterialIcon;

public interface CopyCommandCallback<T extends MaterialWidget & HasCopyCommand> {

    void call(T widget, MaterialIcon clipboardIcon, String text);
}
