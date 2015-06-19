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

import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class MaterialListBox extends com.google.gwt.user.client.ui.ListBox{

	private boolean old = false;
	private boolean disabled = false;
	
	private String textColor = "";
	
	public MaterialListBox() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public HandlerRegistration addAttachHandler(Handler handler) {
		// TODO Auto-generated method stub
		return super.addAttachHandler(handler);
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return super.asWidget();
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		// TODO Auto-generated method stub
		super.fireEvent(event);
	}

	@Override
	public Object getLayoutData() {
		// TODO Auto-generated method stub
		return super.getLayoutData();
	}

	@Override
	public Widget getParent() {
		// TODO Auto-generated method stub
		return super.getParent();
	}

	@Override
	public boolean isAttached() {
		// TODO Auto-generated method stub
		return super.isAttached();
	}

	@Override
	public void onBrowserEvent(Event event) {
		// TODO Auto-generated method stub
		super.onBrowserEvent(event);
	}

	@Override
	public void removeFromParent() {
		// TODO Auto-generated method stub
		super.removeFromParent();
	}

	@Override
	public void setLayoutData(Object layoutData) {
		// TODO Auto-generated method stub
		super.setLayoutData(layoutData);
	}

	@Override
	public void sinkEvents(int eventBitsToAdd) {
		// TODO Auto-generated method stub
		super.sinkEvents(eventBitsToAdd);
	}

	@Override
	public void unsinkEvents(int eventBitsToRemove) {
		// TODO Auto-generated method stub
		super.unsinkEvents(eventBitsToRemove);
	}

	@Override
	protected HandlerManager createHandlerManager() {
		// TODO Auto-generated method stub
		return super.createHandlerManager();
	}

	@Override
	protected void delegateEvent(Widget target, GwtEvent<?> event) {
		// TODO Auto-generated method stub
		super.delegateEvent(target, event);
	}

	@Override
	protected void doAttachChildren() {
		// TODO Auto-generated method stub
		super.doAttachChildren();
	}

	@Override
	protected void doDetachChildren() {
		// TODO Auto-generated method stub
		super.doDetachChildren();
	}

	@Override
	protected int getHandlerCount(Type<?> type) {
		// TODO Auto-generated method stub
		return super.getHandlerCount(type);
	}

	@Override
	protected void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}

	@Override
	protected void onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
	}

	@Override
	protected void onUnload() {
		// TODO Auto-generated method stub
		super.onUnload();
	}

	@Override
	public void addStyleDependentName(String styleSuffix) {
		// TODO Auto-generated method stub
		super.addStyleDependentName(styleSuffix);
	}

	@Override
	public void addStyleName(String style) {
		// TODO Auto-generated method stub
		super.addStyleName(style);
	}

	@Override
	public int getAbsoluteLeft() {
		// TODO Auto-generated method stub
		return super.getAbsoluteLeft();
	}

	@Override
	public int getAbsoluteTop() {
		// TODO Auto-generated method stub
		return super.getAbsoluteTop();
	}

	@Override
	public Element getElement() {
		// TODO Auto-generated method stub
		return super.getElement();
	}

	@Override
	public int getOffsetHeight() {
		// TODO Auto-generated method stub
		return super.getOffsetHeight();
	}

	@Override
	public int getOffsetWidth() {
		// TODO Auto-generated method stub
		return super.getOffsetWidth();
	}

	@Override
	public String getStyleName() {
		// TODO Auto-generated method stub
		return super.getStyleName();
	}

	@Override
	public String getStylePrimaryName() {
		// TODO Auto-generated method stub
		return super.getStylePrimaryName();
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return super.getTitle();
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return super.isVisible();
	}

	@Override
	public void removeStyleDependentName(String styleSuffix) {
		// TODO Auto-generated method stub
		super.removeStyleDependentName(styleSuffix);
	}

	@Override
	public void removeStyleName(String style) {
		// TODO Auto-generated method stub
		super.removeStyleName(style);
	}

	@Override
	public void setHeight(String height) {
		// TODO Auto-generated method stub
		super.setHeight(height);
	}

	@Override
	public void setPixelSize(int width, int height) {
		// TODO Auto-generated method stub
		super.setPixelSize(width, height);
	}

	@Override
	public void setSize(String width, String height) {
		// TODO Auto-generated method stub
		super.setSize(width, height);
	}

	@Override
	public void setStyleDependentName(String styleSuffix, boolean add) {
		// TODO Auto-generated method stub
		super.setStyleDependentName(styleSuffix, add);
	}

	@Override
	public void setStyleName(String style, boolean add) {
		// TODO Auto-generated method stub
		super.setStyleName(style, add);
	}

	@Override
	public void setStyleName(String style) {
		// TODO Auto-generated method stub
		super.setStyleName(style);
	}

	@Override
	public void setStylePrimaryName(String style) {
		// TODO Auto-generated method stub
		super.setStylePrimaryName(style);
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		super.setTitle(title);
	}

	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		super.setVisible(visible);
	}

	@Override
	public void setWidth(String width) {
		// TODO Auto-generated method stub
		super.setWidth(width);
	}

	@Override
	public void sinkBitlessEvent(String eventTypeName) {
		// TODO Auto-generated method stub
		super.sinkBitlessEvent(eventTypeName);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected Element getStyleElement() {
		// TODO Auto-generated method stub
		return super.getStyleElement();
	}

	@Override
	protected com.google.gwt.dom.client.Element resolvePotentialElement() {
		// TODO Auto-generated method stub
		return super.resolvePotentialElement();
	}

	@Override
	protected void setElement(Element elem) {
		// TODO Auto-generated method stub
		super.setElement(elem);
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		onInitMaterialDesign();
	}
	
	public static native void onInitMaterialDesign()/*-{
		$wnd.jQuery(document).ready(function(){
		  $wnd.jQuery('select').material_select();
		});
	}-*/;

	public boolean isOld() {
		return old;
	}

	public void setOld(boolean old) {
		this.old = old;
		if(old){
			this.addStyleName("browser-default input-field");
		}
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
		this.getElement().addClassName(textColor);
	}

	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		// TODO Auto-generated method stub
		onInitMaterialDesign();
		return super.addChangeHandler(handler);
	}

	@Override
	public void addChangeListener(ChangeListener listener) {
		// TODO Auto-generated method stub
		super.addChangeListener(listener);
	}

	@Override
	public void addItem(String item) {
		// TODO Auto-generated method stub
		super.addItem(item);
		onInitMaterialDesign();
	}

	@Override
	public void addItem(String item, Direction dir) {
		// TODO Auto-generated method stub
		super.addItem(item, dir);
		onInitMaterialDesign();
	}

	@Override
	public void addItem(String item, String value) {
		// TODO Auto-generated method stub
		super.addItem(item, value);
		onInitMaterialDesign();
	}

	@Override
	public void addItem(String item, Direction dir, String value) {
		// TODO Auto-generated method stub
		super.addItem(item, dir, value);
		onInitMaterialDesign();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
		onInitMaterialDesign();
	}

	@Override
	public DirectionEstimator getDirectionEstimator() {
		// TODO Auto-generated method stub
		return super.getDirectionEstimator();
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return super.getItemCount();
	}

	@Override
	public String getItemText(int index) {
		// TODO Auto-generated method stub
		return super.getItemText(index);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public int getSelectedIndex() {
		// TODO Auto-generated method stub
		return super.getSelectedIndex();
	}

	@Override
	public String getValue(int index) {
		// TODO Auto-generated method stub
		return super.getValue(index);
	}

	@Override
	public int getVisibleItemCount() {
		// TODO Auto-generated method stub
		return super.getVisibleItemCount();
	}

	@Override
	public void insertItem(String item, int index) {
		// TODO Auto-generated method stub
		super.insertItem(item, index);
	}

	@Override
	public void insertItem(String item, Direction dir, int index) {
		// TODO Auto-generated method stub
		super.insertItem(item, dir, index);
	}

	@Override
	public void insertItem(String item, String value, int index) {
		// TODO Auto-generated method stub
		super.insertItem(item, value, index);
	}

	@Override
	public void insertItem(String item, Direction dir, String value, int index) {
		// TODO Auto-generated method stub
		super.insertItem(item, dir, value, index);
	}

	@Override
	public boolean isItemSelected(int index) {
		// TODO Auto-generated method stub
		return super.isItemSelected(index);
	}

	@Override
	public boolean isMultipleSelect() {
		// TODO Auto-generated method stub
		return super.isMultipleSelect();
	}

	@Override
	public void removeChangeListener(ChangeListener listener) {
		// TODO Auto-generated method stub
		super.removeChangeListener(listener);
	}

	@Override
	public void removeItem(int index) {
		// TODO Auto-generated method stub
		super.removeItem(index);
	}

	@Override
	public void setDirectionEstimator(boolean enabled) {
		// TODO Auto-generated method stub
		super.setDirectionEstimator(enabled);
	}

	@Override
	public void setDirectionEstimator(DirectionEstimator directionEstimator) {
		// TODO Auto-generated method stub
		super.setDirectionEstimator(directionEstimator);
	}

	@Override
	public void setItemSelected(int index, boolean selected) {
		// TODO Auto-generated method stub
		super.setItemSelected(index, selected);
	}

	@Override
	public void setItemText(int index, String text) {
		// TODO Auto-generated method stub
		super.setItemText(index, text);
	}

	@Override
	public void setItemText(int index, String text, Direction dir) {
		// TODO Auto-generated method stub
		super.setItemText(index, text, dir);
	}

	@Override
	public void setMultipleSelect(boolean multiple) {
		// TODO Auto-generated method stub
		super.setMultipleSelect(multiple);
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}

	@Override
	public void setSelectedIndex(int index) {
		// TODO Auto-generated method stub
		super.setSelectedIndex(index);
	}

	@Override
	public void setValue(int index, String value) {
		// TODO Auto-generated method stub
		super.setValue(index, value);
	}

	@Override
	public void setVisibleItemCount(int visibleItems) {
		// TODO Auto-generated method stub
		super.setVisibleItemCount(visibleItems);
	}

	@Override
	protected String getOptionText(OptionElement option) {
		// TODO Auto-generated method stub
		return super.getOptionText(option);
	}

	@Override
	protected void onEnsureDebugId(String baseID) {
		// TODO Auto-generated method stub
		super.onEnsureDebugId(baseID);
	}

	@Override
	protected void setOptionText(OptionElement option, String text, Direction dir) {
		// TODO Auto-generated method stub
		super.setOptionText(option, text, dir);
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
		if(disabled){
			this.getElement().setAttribute("disabled", "true");
		}
	}

	@Override
	public String getSelectedItemText() {
		// TODO Auto-generated method stub
		return super.getSelectedItemText();
	}

	@Override
	public String getSelectedValue() {
		// TODO Auto-generated method stub
		return super.getSelectedValue();
	}

	
	
	
}
