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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.helper.EventHelper;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.js.ClipboardJS;
import gwt.material.design.client.js.CopyCommandData;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialValueBox;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class CopyCommandMixin<T extends AbstractValueWidget> extends AbstractMixin<T> implements HasCopyCommand {

    public static final String COPY_COMMAND = "copy-command";
    public static final String COPY_COMMAND_PARENT = "copy-command-parent";
    public static final String DATA_CLIPBOARD_TEXT = "data-clipboard-text";
    public static final String DATA_CLIPBOARD_ACTION = "data-clipboard-action";

    protected T copyCommandParent;
    protected ClipboardJS clipboardJS;
    protected CopyCommand copyCommand = CopyCommand.OFF;
    protected MaterialIcon icon = new MaterialIcon(IconType.CONTENT_COPY);
    protected CopyCommandLocale locale = new CopyCommandLocale() {
    };
    protected CopyCommandCallback<T> callback;

    public CopyCommandMixin(T copyCommandParent) {
        super(copyCommandParent);

        this.copyCommandParent = copyCommandParent;
        EventHelper.onAttachOnce(uiObject, attachEvent -> setup());
    }

    protected void setup() {
        if (copyCommand != null && copyCommand != CopyCommand.OFF) {
            setupCopyIcon();
            setupValueBox();
            setupClipboardJs();
        } else {
            detachIcon();
        }
    }

    protected void setupCopyIcon() {
        // Setup Widget and Icon
        icon.addStyleName(COPY_COMMAND);
        icon.setId(DOM.createUniqueId());
        copyCommandParent.addStyleName(COPY_COMMAND_PARENT);
        copyCommandParent.add(icon);
        icon.addStyleName(copyCommand.getName());
        icon.addMouseOutHandler(event -> updateTooltip(locale.CopyToClipboard()));
        icon.addClickHandler(event -> {
            icon.getElement().setAttribute(DATA_CLIPBOARD_TEXT, copyCommandParent.getValue() != null ? copyCommandParent.getValue().toString() : "");
        });
    }

    protected void setupValueBox() {
        // Will be checking if we have a value box as parent.
        if (copyCommandParent instanceof MaterialValueBox) {
            MaterialValueBox<?> valueBox = (MaterialValueBox) this.copyCommandParent;
            valueBox.addToggleReadOnlyHandler(event -> checkReadyOnly(valueBox));
            valueBox.addSensitivityChangedHandler(event -> checkReadyOnly(valueBox));
            checkReadyOnly(valueBox);
        }
    }

    protected void setupClipboardJs() {
        updateTooltip(locale.CopyToClipboard());
        clipboardJS = new ClipboardJS("#" + icon.getId());
        clipboardJS.on("success", this::onSuccess);
        clipboardJS.on("error", this::onError);
    }

    protected void checkReadyOnly(MaterialValueBox<?> valueBox) {
        boolean visible = (copyCommand == CopyCommand.ON_READONLY || copyCommand == CopyCommand.ON_READONLY_HOVER)
            && !valueBox.isSensitive()
            && valueBox.isReadOnly();
        icon.getElement().getStyle().setVisibility(visible ? Style.Visibility.VISIBLE : Style.Visibility.HIDDEN);
        Scheduler.get().scheduleDeferred(() -> icon.setEnabled(visible));
    }

    protected void detachIcon() {
        if (icon.isAttached()) {
            icon.removeFromParent();
        }
    }

    protected void updateTooltip(String tooltip) {
        icon.setTooltipPosition(Position.TOP);
        icon.setTooltipDelayMs(0);
        icon.setTooltip(tooltip);
    }

    protected String getStringValue() {
        return copyCommandParent != null && copyCommandParent.getValue() != null ? copyCommandParent.getValue().toString() : "";
    }

    protected boolean onSuccess(CopyCommandData data) {
        if (callback != null) callback.success(copyCommandParent, icon, data);
        updateTooltip(locale.Copied() + ":" + getStringValue());
        $(icon.getElement()).trigger("mouseover", null);
        data.clearSelection();
        return true;
    }

    protected boolean onError(CopyCommandData data) {
        if (callback != null) callback.error(data);
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
