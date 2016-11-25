package gwt.material.design.client.ui;

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

import com.google.gwt.dom.client.Document;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.CssNameMixin;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.SpinnerColor;
import gwt.material.design.client.ui.html.Div;

//@formatter:off

/**
 * Material Spinner , is a circular loader for gwt material apps
 * <h3>UiBinder Usage:</h3>
 * <p>
 * <pre>
 * {@code
 * <m:MaterialSpinner color="RED" />
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#loader">Material Progress</a>
 */
//@formatter:on
public class MaterialSpinner extends MaterialWidget {

    private Div circleClipperLeft = new Div();
    private Div circleClipperRight = new Div();
    private Div circle1 = new Div();
    private Div circle2 = new Div();
    private Div circle3 = new Div();
    private Div gapPatch = new Div();

    private CssNameMixin<MaterialSpinner, SpinnerColor> spinnerColorMixin = new CssNameMixin<>(this);

    public MaterialSpinner() {
        super(Document.get().createDivElement(), CssName.SPINNER_LAYER);
        add(circleClipperLeft);
        circleClipperLeft.add(circle1);
        add(gapPatch);
        gapPatch.add(circle2);
        add(circleClipperRight);
        circleClipperRight.add(circle3);

        circleClipperLeft.setStyleName(CssName.CIRCLE_CLIPPER + " " + CssName.LEFT);
        gapPatch.setStyleName(CssName.GAP_PATCH);
        circleClipperRight.setStyleName(CssName.CIRCLE_CLIPPER + " " + CssName.RIGHT);

        circle1.setStyleName(CssName.CIRCLE);
        circle2.setStyleName(CssName.CIRCLE);
        circle3.setStyleName(CssName.CIRCLE);
    }

    public MaterialSpinner(SpinnerColor spinnerColor) {
        this();
        setColor(spinnerColor);
    }

    public void setColor(SpinnerColor spinnerColor) {
        spinnerColorMixin.setCssName(spinnerColor);
    }

    public SpinnerColor getColor() {
        return spinnerColorMixin.getCssName();
    }
}
