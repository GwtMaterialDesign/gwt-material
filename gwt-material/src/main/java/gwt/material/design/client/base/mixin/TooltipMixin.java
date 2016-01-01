package gwt.material.design.client.base.mixin;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.HasTooltip;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.MaterialTooltip;

import com.google.gwt.user.client.ui.Widget;

/**
 * Mixin for the {@link MaterialTooltip} component.
 * 
 * @author gilberto-torrezan
 * 
 * @see HasTooltip
 * @see MaterialWidget
 */
public class TooltipMixin<H extends Widget & HasTooltip> extends AbstractMixin<H> implements HasTooltip {

    protected MaterialTooltip tooltip;

    public TooltipMixin(H uiObject) {
        super(uiObject);
    }

    protected void initializeTooltip() {
        if (tooltip == null) {
            tooltip = new MaterialTooltip(uiObject);
        }
    }

    @Override
    public String getTooltip() {
        return tooltip == null ? null : tooltip.getText();
    }

    @Override
    public void setTooltip(String tooltip) {
        initializeTooltip();
        this.tooltip.setText(tooltip);
    }

    @Override
    public Position getTooltipPosition() {
        return tooltip == null ? null : tooltip.getPosition();
    }

    @Override
    public void setTooltipPosition(Position position) {
        initializeTooltip();
        this.tooltip.setPosition(position);
    }

    @Override
    public int getTooltipDelayMs() {
        return tooltip == null ? 0 : tooltip.getDelayMs();
    }

    @Override
    public void setTooltipDelayMs(int delayMs) {
        initializeTooltip();
        this.tooltip.setDelayMs(delayMs);
    }

}
