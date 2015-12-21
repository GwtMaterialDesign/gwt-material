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

import com.google.gwt.core.client.JsDate;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.base.mixin.GridMixin;
import gwt.material.design.client.constants.Orientation;
import gwt.material.design.client.ui.html.DateInput;
import gwt.material.design.client.ui.html.Label;

import java.util.Date;

//@formatter:off

/**
 * Material Date Picker will provide a visual calendar to your apps.
 * <p/>
 * <h3>UiBinder Usage:</h3>
 * {@code
 * <m:MaterialDatePicker ui:field="datePicker">
 * }
 * <h3>Java Usage:</h3>
 * {@code
 * datePicker.setDate(new Date());
 * }
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#pickers">Material Date Picker</a>
 */
//@formatter:on
public class MaterialDatePicker extends MaterialWidget implements HasGrid, HasError, HasOrientation, HasPlaceholder {

    /**
     * Enum for identifying various selection types for the picker.
     */
    public enum MaterialDatePickerType {
        DAY,
        MONTH_DAY,
        YEAR_MONTH_DAY,
        YEAR
    }

    /**
     * Delegate interface for handling picker events.
     */
    public interface MaterialDatePickerDelegate {
        /**
         * Called as soon as a click occurs on the calendar widget. !EXPERIMENTAL!
         *
         * @param currDate which is currently selected.
         */
        void onCalendarClose(Date currDate);
    }

    private String placeholder;
    private Date date;
    private Date dateMin;
    private Date dateMax;
    private String format = "dd mmmm yyyy";
    private DateInput dateInput;
    private Label label = new Label();
    private MaterialLabel lblName = new MaterialLabel();
    private Element pickatizedDateInput;
    private MaterialLabel lblError = new MaterialLabel();

    private Orientation orientation = Orientation.PORTRAIT;
    private MaterialDatePickerDelegate delegate;
    private MaterialDatePickerType selectionType = MaterialDatePickerType.DAY;

    private final GridMixin<MaterialDatePicker> gridMixin = new GridMixin<>(this);
    private final ErrorMixin<MaterialDatePicker, MaterialLabel> errorMixin;

    private boolean initialized = false;

    public MaterialDatePicker() {
        super(Document.get().createDivElement());
        addStyleName("input-field");

        dateInput = new DateInput();
        add(dateInput);

        label.add(lblName);
        add(label);

        add(lblError);

        errorMixin = new ErrorMixin<>(this, lblError, dateInput);
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        addStyleName(orientation.getCssName());
        pickatizedDateInput = initDatePicker(dateInput.getElement(), selectionType.name(), format);
        initClickHandler(pickatizedDateInput, this);

        label.getElement().setAttribute("for", getPickerId(pickatizedDateInput));

        this.initialized = true;

        setDate(this.date);
        setDateMin(this.dateMin);
        setDateMax(this.dateMin);
        setPlaceholder(this.placeholder);
    }

    @Override
    public void clear() {
        if (initialized) {
            clearErrorOrSuccess();
            label.removeStyleName("active");
            dateInput.removeStyleName("valid");
        }
    }

    public void removeErrorModifiers() {
        dateInput.addStyleName("valid");
        dateInput.removeStyleName("invalid");
        lblName.removeStyleName("green-text");
        lblName.removeStyleName("red-text");
    }

    /**
     * Sets the type of selection options (date, month, year,...).
     *
     * @param type if <code>null</code>, {@link MaterialDatePickerType#DAY} will be used as fallback.
     */
    public void setDateSelectionType(MaterialDatePickerType type) {
        if (type != null) {
            this.selectionType = type;
        }
    }

    native void initClickHandler(Element picker, MaterialDatePicker parent) /*-{
        picker.pickadate('picker').on({
            close: function () {
                parent.@gwt.material.design.client.ui.MaterialDatePicker::onClose()();
            },
            open: function () {
                parent.@gwt.material.design.client.ui.MaterialDatePicker::onOpen()();
            },
            set: function (thingSet) {

                if (thingSet.hasOwnProperty('clear')) {
                    parent.@gwt.material.design.client.ui.MaterialDatePicker::onClear()();
                }
                else if (thingSet.select) {
                    parent.@gwt.material.design.client.ui.MaterialDatePicker::onSelect()();
                }
            }
        });
    }-*/;

    /**
     * A delegate which implements handling of events from date picker.
     *
     * @param delegate which will be notified on picker events.
     * @see MaterialDatePickerDelegate
     */
    public void setDelegate(MaterialDatePickerDelegate delegate) {
        this.delegate = delegate;
    }

    void onClose() {

        if (delegate != null) {
            delegate.onCalendarClose(getDate());
        }
    }

    void onOpen() {
        label.addStyleName("active");
        dateInput.setFocus(true);
    }

    void onSelect() {
        label.addStyleName("active");
        dateInput.addStyleName("valid");
    }

    void onClear() {
        clear();
    }

    public static native String getPickerId(Element inputSrc) /*-{
        return inputSrc.pickadate('picker').get("id");
    }-*/;

    public static native Element initDatePicker(Element inputSrc, String typeName, String format) /*-{
        var input;
        if (typeName === "MONTH_DAY") {
            input = $wnd.jQuery(inputSrc).pickadate({
                container: 'body',
                selectYears: false,
                selectMonths: true,
                format: format
            });
        } else if (typeName === "YEAR_MONTH_DAY") {
            input = $wnd.jQuery(inputSrc).pickadate({
                container: 'body',
                selectYears: true,
                selectMonths: true,
                format: format
            });
        } else if (typeName === "YEAR") {
            input = $wnd.jQuery(inputSrc).pickadate({
                container: 'body',
                selectYears: true,
                format: format
            });
        } else {
            input = $wnd.jQuery(inputSrc).pickadate({
                container: 'body',
                format: format
            });
        }

        return input;
    }-*/;

    /**
     * Sets the current date of the picker.
     *
     * @param date - must not be <code>null</code>
     */
    public void setDate(Date date) {
        if (date == null) {
            return;
        }
        this.date = date;
        if (initialized) {
            setPickerDate(JsDate.create((double) date.getTime()), pickatizedDateInput);
            label.addStyleName("active");
        }
    }

    public Date getDateMin() {
        return dateMin;
    }

    public void setDateMin(Date dateMin) {
        this.dateMin = dateMin;
        if (initialized && dateMin != null) {
            setPickerDateMin(JsDate.create((double) dateMin.getTime()), pickatizedDateInput);
        }
    }

    public native void setPickerDateMin(JsDate date, Element picker) /*-{
        picker.pickadate('picker').set('min', date);
    }-*/;

    public Date getDateMax() {
        return dateMax;
    }

    public void setDateMax(Date dateMax) {
        this.dateMax = dateMax;
        if (initialized && dateMax != null) {
            setPickerDateMax(JsDate.create((double) dateMax.getTime()), pickatizedDateInput);
        }
    }

    public native void setPickerDateMax(JsDate date, Element picker) /*-{
        picker.pickadate('picker').set('max', date);
    }-*/;

    public native void setPickerDate(JsDate date, Element picker) /*-{
        picker.pickadate('picker').set('select', date, { muted: true });
    }-*/;

    public Date getDate() {
        return getPickerDate();
    }

    public Date getPickerDate() {
        try {
            JsDate selectedDate = getDatePickerValue(pickatizedDateInput);
            return new Date((long) selectedDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static native JsDate getDatePickerValue(Element picker)/*-{
        return picker.pickadate('picker').get('select').obj;
    }-*/;

    public native void clearValues(Element picker) /*-{
        picker.pickadate('picker').clear();
    }-*/;

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;

        if (initialized && placeholder != null) {
            lblName.setText(placeholder);
        }
    }

    public MaterialDatePickerType getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(MaterialDatePickerType selectionType) {
        if(initialized) {
            throw new IllegalStateException("setSelectionType can be called only before initialization");
        }
        this.selectionType = selectionType;
    }

    /**
     * @return the orientation
     */
    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * @param orientation the orientation to set : can be Vertical or Horizontal
     */
    @Override
    public void setOrientation(Orientation orientation) {
        if(initialized) {
            throw new IllegalStateException("setOrientation can be called only before initialization");
        }
        this.orientation = orientation;
    }

    @Override
    public void setGrid(String grid) {
        gridMixin.setGrid(grid);
    }

    @Override
    public void setOffset(String offset) {
        gridMixin.setOffset(offset);
    }

    @Override
    public void setError(String error) {
        errorMixin.setError(error);

        removeErrorModifiers();
        lblName.setStyleName("red-text");
        dateInput.addStyleName("invalid");

    }

    @Override
    public void setSuccess(String success) {
        errorMixin.setSuccess(success);
        lblName.setStyleName("green-text");
        dateInput.addStyleName("valid");
    }

    @Override
    public void clearErrorOrSuccess() {
        errorMixin.clearErrorOrSuccess();
        removeErrorModifiers();
    }

    public String getFormat() {
        return format;
    }

    /**
     * To call before initialization
     * @param format
     */
    public void setFormat(String format) {
        if(initialized) {
            throw new IllegalStateException("setFormat can be called only before initialization");
        }
        this.format = format;
    }
}
