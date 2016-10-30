/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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

import gwt.material.design.client.base.mixin.TooltipMixin;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.MaterialTooltip;

/**
 * Interface that determines the class has a {@link MaterialTooltip} attached to
 * it.
 *
 * @author gilberto-torrezan
 * @see TooltipMixin
 * @see MaterialWidget
 */
public interface HasTooltip {

    /**
     * @return the current tooltip text to be shown
     */
    String getTooltip();

    /**
     * Sets the tooltip text to be shown for the component.
     */
    void setTooltip(String tooltip);

    /**
     * @return the position where the tooltip text should appear, relative to
     * the component
     */
    Position getTooltipPosition();

    /**
     * Sets the {@link Position} where the tooltip text should appear, relative
     * to the component.
     */
    void setTooltipPosition(Position position);

    /**
     * @return the delay in ms used to show the tooltip
     */
    int getTooltipDelayMs();

    /**
     * Sets the time in ms to show the tooltip text for the component
     */
    void setTooltipDelayMs(int delayMs);

    void setTooltipHTML(String html);

    String getTooltipHTML();

}
