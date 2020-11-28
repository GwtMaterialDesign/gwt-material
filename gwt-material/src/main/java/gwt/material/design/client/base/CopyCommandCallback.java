package gwt.material.design.client.base;

import gwt.material.design.client.js.CopyCommandData;
import gwt.material.design.client.ui.MaterialIcon;

public interface CopyCommandCallback<T extends MaterialWidget & HasCopyCommand> {

    void success(T widget, MaterialIcon clipboardIcon, CopyCommandData data);

    void error(CopyCommandData data);
}
