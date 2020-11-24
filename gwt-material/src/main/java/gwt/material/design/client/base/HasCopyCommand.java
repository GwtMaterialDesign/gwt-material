package gwt.material.design.client.base;

import gwt.material.design.client.base.mixin.CopyCommandCallback;
import gwt.material.design.client.ui.MaterialIcon;

public interface HasCopyCommand {

    /**
     * Will append a copy to clipboard icon to the referenced widget.
     */
    void setCopyCommand(CopyCommand copyCommand);

    CopyCommand getCopyCommand();

    /**
     * Will set the copy command callback. Which contains the text, widget and the icon.
     */
    void setCopyCommandCallback(CopyCommandCallback callback);

    /**
     * Support copy command localization specially tooltips.
     */
    void setCopyCommandLocale(CopyCommandLocale locale);

    /**
     * Apply a different icon to your copy command
     */
    void setCopyCommandIcon(MaterialIcon icon);

    MaterialIcon getCopyCommandIcon();
}
