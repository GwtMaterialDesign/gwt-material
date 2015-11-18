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

import gwt.material.design.client.base.HasGrid;
import gwt.material.design.client.base.HasError;
import gwt.material.design.client.constants.Orientation;

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import gwt.material.design.client.base.HasOrientation;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.base.mixin.GridMixin;

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
        initDatePicker(id, selectionType.name(), this);
        initClickHandler(id, this);
        panel.add(lblError);
    }

    @Override
    public void clear() {
        super.clear();
        clearErrorOrSuccess();
    }

    public static native void initDatePicker(String id, String typeName, MaterialDatePicker parent) /*-{
        var input;
        if(typeName === "MONTH_DAY") {
            input = $wnd.jQuery('#' + id).pickadate({
                container: 'body',
                selectYears: false,
                selectMonths: true
            });
        } else if(typeName === "YEAR_MONTH_DAY") {
            input = $wnd.jQuery('#' + id).pickadate({
                container: 'body',
                selectYears: true,
                selectMonths: true
            });
        } else if(typeName === "YEAR"){
            input = $wnd.jQuery('#' + id).pickadate({
                container: 'body',
                selectYears: true
            });
        } else {
            input = $wnd.jQuery('#' + id).pickadate({
                container: 'body'
            });
        }

        parent.@gwt.material.design.client.ui.MaterialDatePicker::input = input;
    }-*/;

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

    public Date getDate() {
        return getPickerDate();
    }

    public native void clearValues() /*-{
        $wnd.jQuery('.datepicker').val("");
    }-*/;

    public Date getPickerDate() {
        try {
            DateTimeFormat sdf = DateTimeFormat.getFormat("d MMM, yyyy");
            date = sdf.parse(getDatePickerValue(id));
            return date;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Sets the current date of the picker.
     * @param date - must not be <code>null</code>
     */
    public void setDate(Date date) {
        if(date == null) {
            return;
        }
        this.date = date;
        initDatePicker(id, selectionType.name(), this);
        DateTimeFormat sdf = DateTimeFormat.getFormat("d MMM, yyyy");
        setDatePickerValue(sdf.format(date), id);
        /*DateTimeFormat sdfSetter = DateTimeFormat.getFormat("yyyy-MM-dd");
        selectDate(sdfSetter.format(date), id, this);*/
    }

    private native void selectDate(String date, String id, MaterialDatePicker parent) /*-{
        var input = parent.@gwt.material.design.client.ui.MaterialDatePicker::input;
        var picker = input.pickadate('picker');
        if(picker) {
            picker.set('select', date, { format: 'yyyy-MM-dd' });
        }
    }-*/;

    public static native String getDatePickerValue(String id)/*-{
        return $wnd.jQuery('#' + id).val();
    }-*/;

    private static native void setDatePickerValue(String value, String id)/*-{
        var input = $wnd.jQuery('.datepicker').pickadate();

        // Use the picker object directly.
        var picker = input.pickadate('picker');
        if(picker) {
            picker.set('select', value, { format: 'yyyy-MM-dd' });
        }
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
}
