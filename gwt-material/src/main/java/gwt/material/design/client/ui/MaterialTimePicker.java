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

import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.constants.Orientation;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

//@formatter:off
/**
* Material Time Picker -  provide a simple way to select a single value from a pre-determined set.
* <h3>UiBinder Usage:</h3>
* <pre>
*{@code <m:MaterialTimePicker placeholder="Time Arrival" />
* </pre>
* @see <a href="http://gwt-material-demo.herokuapp.com/#pickers">Material Pickers</a>
* @author kevzlou7979
* @author Ben Dol
*/
//@formatter:on
public class MaterialTimePicker extends MaterialWidget implements HasError, HasPlaceholder, HasOrientation {

    MaterialPanel panel = new MaterialPanel();
    MaterialInput input = new MaterialInput();

    private String time;
    private String placeholder;
    private boolean autoClose;
    private Orientation orientation = Orientation.PORTRAIT;

    private MaterialLabel lblError = new MaterialLabel();

    private final ErrorMixin<MaterialTimePicker, MaterialLabel> errorMixin = new ErrorMixin<>(this, lblError, input);

    public MaterialTimePicker() {
        super(Document.get().createElement("div"));
        panel.add(lblError);
        input.setType(InputType.TEXT);
        panel.add(input);
        add(panel);
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        input.getElement().setAttribute("type", "text");
        initTimePicker();
    }

    public void reset() {
        setTime("");
        clearErrorOrSuccess();
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
        input.getElement().setAttribute("value", time.toUpperCase());
    }

    public boolean isAutoClose() {
        return autoClose;
    }

    public void setAutoClose(boolean autoClose) {
        this.autoClose = autoClose;
    }

    /**
     * @return the placeholder
     */
    @Override
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * @param placeholder the placeholder to set
     */
    @Override
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        input.getElement().setAttribute("placeholder", "Time");
    }

    /**
     * @return the orientation
     */
    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * @param orientation the orientation to set : can be Horizontal or Vertical
     */
    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public void setError(String error) {
        errorMixin.setError(error);
    }

    @Override
    public void setSuccess(String success) {
        errorMixin.setSuccess(success);
    }

    @Override
    public void clearErrorOrSuccess() {
        errorMixin.clearErrorOrSuccess();
    }

    public void initTimePicker() {
        initTimePicker(input.getElement(), getOrientation().getCssName(), isAutoClose());
    }

    protected native void initTimePicker(Element e, String orientation, boolean autoClose) /*-{
        $wnd.jQuery(e).lolliclock({
            autoclose: autoClose,
            orientation: orientation
        });
        $wnd.jQuery(e).blur();
    }-*/;
}
