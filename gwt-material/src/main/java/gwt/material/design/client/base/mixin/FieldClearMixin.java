/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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

import com.google.gwt.dom.client.Style;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialValueBox;

public class FieldClearMixin<T extends MaterialValueBox> extends AbstractMixin<T> {

    private T valueBox;
    private boolean enableClear;
    private MaterialIcon clear = new MaterialIcon(IconType.CLOSE);

    public FieldClearMixin(final T valueBox) {
        super(valueBox);

        this.valueBox = valueBox;
        this.clear.setLayoutPosition(Style.Position.ABSOLUTE);
        this.clear.setRight(16);
        this.clear.setTop(12);
        this.clear.setBackgroundColor(Color.WHITE);
        this.clear.addStyleName("clear-field");

        setEnableClear(false);
    }

    public void load() {
        if (!clear.isAttached()) {
            valueBox.add(clear);
        }

        clear.setVisible(false);
        valueBox.addKeyUpHandler(event -> {
            Object value = valueBox.getValue();
            boolean showClear = value != null && !value.toString().isEmpty() && enableClear;
            clear.setVisible(showClear);
        });

        clear.addClickHandler(event -> {
            valueBox.reset();
            valueBox.setValue(null, true);
            clear.setVisible(false);
        });

        clear.addValueChangeHandler(event -> {
            String value = event.getValue();
            clear.setVisible(value == null || value.isEmpty());
        });
    }

    public void setEnableClear(boolean enableClear) {
        this.enableClear = enableClear;
    }

    public void reset() {
        clear.setVisible(false);
    }
}
