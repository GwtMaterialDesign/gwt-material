/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2021 GwtMaterialDesign
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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;

import java.util.ArrayList;
import java.util.List;

public class MaterialButtonGroup extends MaterialWidget {

    private List<MaterialButton> buttons = new ArrayList<>();
    private MaterialButton current = new MaterialButton();

    public MaterialButtonGroup() {
        super(Document.get().createDivElement(), "button-group");
    }

    @Override
    public void add(Widget child) {
        super.add(child);

        if (child instanceof MaterialButton) {
            MaterialButton button = (MaterialButton) child;
            button.addClickHandler(event -> current = button);
            buttons.add(button);
        }
    }

    public void showLoading(MaterialButton current) {
        this.current = current;

        if (current != null) {
            current.getAsyncDisplayLoader().loading();
            setEnabled(false);
        }
    }

    public void hideLoading() {
        if (current != null) {
            current.getAsyncDisplayLoader().finalize();
        }
        setEnabled(true);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        for (MaterialButton button : buttons) {
            button.setEnabled(enabled);
        }
    }

    @Override
    public void clear() {
        super.clear();

        buttons.clear();
    }

    public MaterialButton getCurrent() {
        return current;
    }

    public void setCurrent(MaterialButton current) {
        this.current = current;
    }

    public MaterialButton get(int index) {
        return buttons.get(index);
    }

    public List<MaterialButton> getButtons() {
        return buttons;
    }
}
