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
package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.base.mixin.ColorsMixin;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.ProgressType;
import gwt.material.design.client.ui.html.Div;

//@formatter:off

/**
 * Material Progress indicator to define intermediate and determinate progress bars
 * <h3>UiBinder Usage:</h3>
 * <p>
 * <pre>
 * {@code
 * // INDETERMINATE Type
 * <m:MaterialProgress type="INDETERMINATE" />
 *
 * // DETERMINATE Type
 * <m:MaterialProgress type="DETERMINATE" percent="80" />
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#loader">Material Progress</a>
 */
public class MaterialProgress extends Div implements HasType<ProgressType> {

    private Div div = new Div();
    private double percent = 0;

    private final ColorsMixin<Div> colorsMixin = new ColorsMixin<>(div);
    private final CssTypeMixin<ProgressType, MaterialProgress> typeMixin = new CssTypeMixin<>(this, div);

    public MaterialProgress() {
        super(CssName.PROGRESS);
        getElement().getStyle().setMargin(0, Unit.PX);
        add(div);
        setType(ProgressType.INDETERMINATE);
    }

    public MaterialProgress(ProgressType type) {
        this();
        setType(type);
    }

    public MaterialProgress(ProgressType type, Double percent) {
        this(type);
        setPercent(percent);
    }

    @Override
    public void setType(ProgressType type) {
        typeMixin.setType(type);
    }

    @Override
    public ProgressType getType() {
        return typeMixin.getType();
    }

    /**
     * Get progress width as percent unit
     */
    public double getPercent() {
        return percent;
    }

    /**
     * Set progress width as percent unit.
     */
    public void setPercent(double percent) {
        this.percent = percent;
        if (percent <= 100) {
            div.getElement().getStyle().setWidth(percent, Unit.PCT);
        } else {
            GWT.log("Progress percent must not exceed to 100 percent.", new RuntimeException());
        }
    }

    /**
     * Get the progress bar color.
     */
    public Color getColor() {
        return colorsMixin.getBackgroundColor();
    }

    /**
     * Set the color of the progress bar.
     *
     * @param color String value of the color.
     */
    public void setColor(Color color) {
        colorsMixin.setBackgroundColor(color);
    }
}