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

import com.google.gwt.dom.client.Style.Unit;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.constants.ProgressType;
import gwt.material.design.client.ui.html.Div;

//@formatter:off

/**
* Material Progress indicator to define intermediate and determinate progress bars
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
* <m:MaterialProgress />
}
* </pre>
* @see <a href="http://gwt-material-demo.herokuapp.com/#loaders">Material Progress</a>
* @author kevzlou7979
*/
public class MaterialProgress extends Div implements HasType<ProgressType> {

    private Div div = new Div();
    private double percent = 0;

    public MaterialProgress() {
        super();
        setStyleName("progress");
        getElement().getStyle().setMargin(0, Unit.PX);
        add(div);
        setType(ProgressType.INDETERMINATE);
    }

    @Override
    public void setType(ProgressType type) {
        div.setStyleName(type.getCssName());
    }

    @Override
    public ProgressType getType() {
        return ProgressType.fromStyleName(getType().getCssName());
    }

    /**
     * Get progress width as percent unit
     * @return
     */
    public double getPercent() {
        return percent;
    }

    /**
     * Set progress width as percent unit
     * @param percent
     */
    public void setPercent(double percent) {
        this.percent = percent;
        div.getElement().getStyle().setWidth(percent, Unit.PCT);
    }
}
