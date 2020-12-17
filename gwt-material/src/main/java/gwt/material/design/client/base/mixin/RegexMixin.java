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
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.base.HasRegex;
import gwt.material.design.client.base.helper.EventHelper;
import gwt.material.design.client.events.PasteEvent;
import gwt.material.design.client.events.RegexValidationEvent;
import gwt.material.design.client.ui.MaterialValueBox;

public class RegexMixin<T extends MaterialValueBox> extends AbstractMixin<T> implements HasRegex {

    protected String regex;
    protected String replaceRegex;
    protected HandlerRegistration keyPressHandler, pasteHandler, blurHandler;

    public RegexMixin(final T uiObject) {
        super(uiObject);

        EventHelper.onAttachOnce(uiObject, attachEvent -> setup());
    }

    protected void setup() {
        if (regex != null && !regex.isEmpty()) {
            keyPressHandler = uiObject.addKeyPressHandler(this::matchRegexOnKeyPress);
            pasteHandler = uiObject.addPasteHandler(this::matchRegexOnPaste);
            blurHandler = uiObject.addBlurHandler(this::matchRegexOnBlur);
        } else {
            if (keyPressHandler != null) keyPressHandler.removeHandler();
            if (pasteHandler != null) pasteHandler.removeHandler();
        }
    }

    protected void matchRegexOnKeyPress(KeyPressEvent event) {
        String value = String.valueOf(event.getCharCode());
        boolean matches = value.matches(regex);
        if (!matches) {
            uiObject.getValueBoxBase().cancelKey();
        }
        RegexValidationEvent.fire(uiObject, event, value, regex, matches);
    }

    protected void matchRegexOnPaste(PasteEvent event) {
        Scheduler.get().scheduleDeferred(() -> {
            String value = event.getValue().replaceAll(replaceRegex, "");
            boolean matches = value.matches(regex);
            uiObject.clear();
            if (matches) {
                uiObject.setText(value);
            }
            RegexValidationEvent.fire(uiObject, event, event.getValue(), regex, matches);
        });
    }

    protected void matchRegexOnBlur(BlurEvent event) {
        String value = uiObject.getText();
        if (value != null) {
            boolean matches = value.matches(regex);
            if (!matches) {
                uiObject.clear();
            }
            RegexValidationEvent.fire(uiObject, event, value, regex, matches);
        }
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public void setRegex(String regex, String replaceRegex) {
        this.regex = regex;
        this.replaceRegex = replaceRegex;
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public HandlerRegistration addRegexValidationHandler(RegexValidationEvent.RegexValidationHandler handler) {
        return uiObject.addHandler(handler, RegexValidationEvent.getType());
    }
}
