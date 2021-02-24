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

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class SelectionToggleHandler<T extends Widget & HasValue<Boolean>> {

    protected T widget;

    protected SelectionToggleHandler() {
    }

    public SelectionToggleHandler(T widget) {
        this.widget = widget;
    }

    public void load() {
        $(widget.getElement()).keydown(e -> {
            if (e.getKeyCode() == KeyCodes.KEY_ENTER) {
                widget.setValue(!widget.getValue(), true);
            }
            return true;
        });
    }

    public void unload() {
        $(widget.getElement()).off("keydown");
    }
}
