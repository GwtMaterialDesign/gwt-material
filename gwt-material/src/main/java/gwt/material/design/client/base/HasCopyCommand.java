/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2020 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.client.base;

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
