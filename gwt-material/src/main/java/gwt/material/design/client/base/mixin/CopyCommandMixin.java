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
package gwt.material.design.client.base.mixin;

import com.google.gwt.user.client.DOM;
import gwt.material.design.client.base.*;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.js.ClipboardJS;
import gwt.material.design.client.js.CopyCommandData;
import gwt.material.design.client.ui.MaterialIcon;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class CopyCommandMixin<T extends AbstractValueWidget & HasReadOnly & HasCopyCommand> extends AbstractMixin<T> implements HasCopyCommand {

    public static final String COPY_COMMAND = "copy-command";
    public static final String DATA_CLIPBOARD_TEXT = "data-clipboard-text";
    public static final String DATA_CLIPBOARD_ACTION = "data-clipboard-action";

    protected T valueBox;
    protected ClipboardJS clipboardJS;
    protected CopyCommand copyCommand = CopyCommand.OFF;
    protected MaterialIcon icon = new MaterialIcon(IconType.CONTENT_COPY);
    protected CopyCommandLocale locale = new CopyCommandLocale() {
    };
    protected CopyCommandCallback<T> callback;

    public CopyCommandMixin(T valueBox) {
        super(valueBox);

        this.valueBox = valueBox;
        this.valueBox.addAttachHandler(event -> {
            if (event.isAttached()) setup();
        });
    }

    protected void setup() {
        if (copyCommand != null && copyCommand != CopyCommand.OFF) {
            // Setup Widget and Icon
            icon.addStyleName(COPY_COMMAND);
            icon.setId(DOM.createUniqueId());
            valueBox.add(icon);
            icon.addStyleName(copyCommand.getName());
            icon.addMouseOutHandler(event -> updateTooltip(locale.CopyToClipboard()));
            icon.addClickHandler(event -> {
                icon.getElement().setAttribute(DATA_CLIPBOARD_TEXT, valueBox.getValue() != null ? valueBox.getValue().toString() : "");
                if (valueBox.isReadOnly()) {
                    valueBox.setEnabled(true);
                }
            });
            if (copyCommand == CopyCommand.ON_READONLY && !valueBox.isReadOnly()) icon.setVisible(false);

            // Initialize Clipboard Js
            updateTooltip(locale.CopyToClipboard());
            clipboardJS = new ClipboardJS("#" + icon.getId());
            clipboardJS.on("success", this::onSuccess);
            clipboardJS.on("error", this::onError);
        } else {
            if (icon.isAttached()) {
                icon.removeFromParent();
            }
        }
    }

    protected void updateTooltip(String tooltip) {
        icon.setTooltipPosition(Position.TOP);
        icon.setTooltipDelayMs(0);
        icon.setTooltip(tooltip);
    }

    protected String getStringValue() {
        return valueBox != null && valueBox.getValue() != null ? valueBox.getValue().toString() : "";
    }

    protected boolean onSuccess(CopyCommandData data) {
        if (callback != null) callback.success(valueBox, icon, data);
        updateTooltip(locale.Copied() + ":" + getStringValue());
        $(icon.getElement()).trigger("mouseover", null);
        data.clearSelection();
        if (valueBox.isReadOnly()) {
            valueBox.setEnabled(false);
        }
        return true;
    }

    protected boolean onError(CopyCommandData data) {
        if (callback != null) callback.error(data);
        if (valueBox.isReadOnly()) {
            valueBox.setEnabled(false);
        }
        return false;
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
