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

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;

public class MaterialDatePicker extends FocusPanel{

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
	
	private HTMLPanel panel;
	private Date date;
	private String placeholder;
	private String id;
	private MaterialDatePickerDelegate delegate;
	private MaterialDatePickerType selectionType = MaterialDatePickerType.DAY;
	JavaScriptObject input;
	public MaterialDatePicker() {
	
	}
		
	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		id = String.valueOf(hashCode());
		this.clear();
		panel = new HTMLPanel("<input placeholder='"+placeholder+"' type='date' id='"+id+"' class='datepicker'>");
		this.add(panel);
		initDatePicker(id, selectionType.name(), this);
		initClickHandler(id, this);
	}

	public static native void initDatePicker(String id, String typeName, MaterialDatePicker parent)/*-{
		var input;
		if(typeName === "MONTH_DAY") {
			input = $wnd.jQuery('#' + id).pickadate({
				selectYears: false,
				selectMonths: true
			});
		} else if(typeName === "YEAR_MONTH_DAY") {
			input = $wnd.jQuery('#' + id).pickadate({
				selectYears: true,
				selectMonths: true
			});
		} else {
			input = $wnd.jQuery('#' + id).pickadate();
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
	
	public static native String getDatePickerValue(String id)/*-{
		var color = $wnd.jQuery('#' + id).val();  
		return color;
	}-*/;
	
	private static native void setDatePickerValue(String value, String id)/*-{
        $wnd.jQuery('#' + id).val(value);
	}-*/;
	
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

	public static native void clearValues()/*-{
		$wnd.jQuery('.datepicker').val("");
	}-*/;
	
	public Date getPickerDate(){
		try{
			DateTimeFormat sdf = DateTimeFormat.getFormat("d MMM, yyyy");
			date = sdf.parse(getDatePickerValue(id));
			return date;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Sets the current date of the picker.
	 * @param date - must not be <code>null</code>
	 */
	public void setDate(Date date) {
		this.date = date;
		DateTimeFormat sdf = DateTimeFormat.getFormat("d MMM, yyyy");
		setDatePickerValue(sdf.format(date), id);
		DateTimeFormat sdfSetter = DateTimeFormat.getFormat("yyyy-MM-dd");
		selectDate(sdfSetter.format(date), id, this);
	}
	
	private native void selectDate(String date, String id, MaterialDatePicker parent) /*-{
		var input = parent.@gwt.material.design.client.ui.MaterialDatePicker::input;
		var picker = input.pickadate('picker');
		if(picker) {
			picker.set('select', date, { format: 'yyyy-mm-dd' });
		}
	}-*/;

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	
	/**
	 * Enum for identifying various selection types for the picker.
	 *
	 */
	public enum MaterialDatePickerType {
		DAY,
		MONTH_DAY,
		YEAR_MONTH_DAY
	}
	
}
