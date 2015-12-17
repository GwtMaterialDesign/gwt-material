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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import gwt.material.design.client.base.HasError;
import gwt.material.design.client.base.HasGrid;
import gwt.material.design.client.base.HasOrientation;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.base.mixin.GridMixin;
import gwt.material.design.client.constants.Orientation;

import java.util.Date;

//@formatter:off

/**
 * Material Date Picker will provide a visual calendar to your apps.
 *
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
public class MaterialDatePicker extends FocusPanel implements HasGrid, HasError, HasOrientation {

    /**
     * Enum for identifying various selection types for the picker.
     *
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
         * @param currDate which is currently selected.
         */
        void onCalendarClose(Date currDate);
    }

    private String placeholder;
    private String id;
    private Date date;
    private String format = "dd mmmm yyyy";
    private Date dateMin = null;
    private Date dateMax = null;

    private JavaScriptObject input;
    private HTMLPanel panel;
    private MaterialLabel lblError = new MaterialLabel();

    private Orientation orientation = Orientation.PORTRAIT;
    private MaterialDatePickerDelegate delegate;
    private MaterialDatePickerType selectionType = MaterialDatePickerType.DAY;

    private final GridMixin<MaterialDatePicker> gridMixin = new GridMixin<>(this);
    private final ErrorMixin<MaterialDatePicker, MaterialLabel> errorMixin = new ErrorMixin<>(this, lblError, panel);

    public MaterialDatePicker() {
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        id = String.valueOf(hashCode());
        clear();
        panel = new HTMLPanel("<input placeholder='"+placeholder+"' type='date' id='"+id+"' class='datepicker'>");
        this.add(panel);
        panel.addStyleName(orientation.getCssName());
        if(dateMin != null && dateMax != null) {
            initDatePicker(id, selectionType.name(), this, format, dateMin.toString(), dateMax.toString());
        }else{
            initDatePicker(id, selectionType.name(), this, format);
        }
        initClickHandler(id, this);
        panel.add(lblError);
    }

    @Override
    public void clear() {
        super.clear();
        clearErrorOrSuccess();
    }

    /**
     * Sets the type of selection options (date, month, year,...).
     * @param type if <code>null</code>, {@link MaterialDatePickerType#DAY} will be used as fallback.
     */
    public void setDateSelectionType(MaterialDatePickerType type) {
        if(type == null) {
            type = MaterialDatePickerType.DAY;
        }
        this.selectionType = type;
    }

    native void initClickHandler(String id, MaterialDatePicker parent) /*-{
        var input = parent.@gwt.material.design.client.ui.MaterialDatePicker::input;
        var picker = input.pickadate('picker');
        picker.on({
            close: function() {
                parent.@gwt.material.design.client.ui.MaterialDatePicker::notifyDelegate()();
            }
        });
    }-*/;

    /**
     * A delegate which implements handling of events from date picker.
     * @param delegate which will be notified on picker events.
     * @see MaterialDatePickerDelegate
     */
    public void setDelegate(MaterialDatePickerDelegate delegate) {
        this.delegate = delegate;
    }

    void notifyDelegate() {
        if(delegate != null) {
            delegate.onCalendarClose(getDate());
        }
    }

    public static native void initDatePicker(String id, String typeName, MaterialDatePicker parent, String format, String dateMin, String dateMax) /*-{
        var input;
        if(typeName === "MONTH_DAY") {
            input = $wnd.jQuery('#' + id).pickadate({
                container: 'body',
                selectYears: false,
                selectMonths: true,
                format: format,
                min: new Date(dateMin),
                max: new Date(dateMax)
            });
        } else if(typeName === "YEAR_MONTH_DAY") {
            input = $wnd.jQuery('#' + id).pickadate({
                container: 'body',
                selectYears: true,
                selectMonths: true,
                format: format,
                min: new Date(dateMin),
                max: new Date(dateMax)
            });
        } else if(typeName === "YEAR"){
            input = $wnd.jQuery('#' + id).pickadate({
                container: 'body',
                selectYears: true,
                format: format,
                min: new Date(dateMin),
                max: new Date(dateMax)
            });
        } else {
            input = $wnd.jQuery('#' + id).pickadate({
                container: 'body',
                format: format,
                min: new Date(dateMin),
                max: new Date(dateMax)
            });
        }

        parent.@gwt.material.design.client.ui.MaterialDatePicker::input = input;
    }-*/;

    public static native void initDatePicker(String id, String typeName, MaterialDatePicker parent, String format) /*-{
        var input;
        if(typeName === "MONTH_DAY") {
            input = $wnd.jQuery('#' + id).pickadate({
                container: 'body',
                selectYears: false,
                selectMonths: true,
                format: format
            });
        } else if(typeName === "YEAR_MONTH_DAY") {
            input = $wnd.jQuery('#' + id).pickadate({
                container: 'body',
                selectYears: true,
                selectMonths: true,
                format: format
            });
        } else if(typeName === "YEAR"){
            input = $wnd.jQuery('#' + id).pickadate({
                container: 'body',
                selectYears: true,
                format: format
            });
        } else {
            input = $wnd.jQuery('#' + id).pickadate({
                container: 'body',
                format: format
            });
        }

        parent.@gwt.material.design.client.ui.MaterialDatePicker::input = input;
    }-*/;

    /**
     * Sets the current date of the picker.
     * @param date - must not be <code>null</code>
     */
    public void setDate(Date date) {
        if(date == null) {
            return;
        }
        this.date = date;
        DateTimeFormat sdf = DateTimeFormat.getFormat(format);
        setPickerDate(sdf.format(date), id);
    }

    public native void setPickerDate(String date, String id) /*-{
        var input = $wnd.jQuery('#' + id).pickadate();
        var picker = input.pickadate('picker');
        picker.set('select', date);
    }-*/;

    public Date getDate() {
        return getPickerDate();
    }

    public Date getPickerDate() {
        try {
            DateTimeFormat sdf = DateTimeFormat.getFormat("mm/dd/yyyy");
            date = sdf.parse(getDatePickerValue(id));
            return date;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static native String getDatePickerValue(String id)/*-{

        function pad(n) {
            return (n < 10) ? ("0" + n) : n;
        }

        var picker = $wnd.jQuery('#' + id).pickadate('picker');
        var formattedDate = new Date(picker.get('value'));
        var d = pad(formattedDate.getDate());
        var m =  pad(formattedDate.getMonth());
        m += 1;  // JavaScript months are 0-11
        var y = formattedDate.getFullYear();
        return  m + "/" + d + "/" + y;
    }-*/;

    public native void clearValues() /*-{
        $wnd.jQuery('.datepicker').val("");
    }-*/;

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public MaterialDatePickerType getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(MaterialDatePickerType selectionType) {
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
    }

    @Override
    public void setSuccess(String success) {
        errorMixin.setSuccess(success);
    }

    @Override
    public void clearErrorOrSuccess() {
        errorMixin.clearErrorOrSuccess();
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setDateLimit(Date dateMin, Date dateMax) {
        this.dateMin = dateMin;
        this.dateMax = dateMax;
    }


}
