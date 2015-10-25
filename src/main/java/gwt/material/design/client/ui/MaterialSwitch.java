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

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.CustomInput;
import gwt.material.design.client.custom.CustomLabel;
import gwt.material.design.client.custom.CustomSpan;
import gwt.material.design.client.custom.HasError;
import gwt.material.design.client.custom.HasGrid;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;

//@formatter:off
/**
* Material Switch or other call it toggle - used for an alternative for checkbox
* 
* <p>
* <h3>UiBinder Usage:</h3>
* <pre>
* {@code 
* <m:MaterialSwitch value="true"/>
* <m:MaterialSwitch value="true" disabled="true"/>
* 
* }
* </pre>
* </p>
* 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material Switch</a>
*/
//@formatter:on
public class MaterialSwitch extends ComplexWidget implements HasClickHandlers, HasGrid, HasError{

	private CustomInput input = new CustomInput();
	private CustomSpan span = new CustomSpan();
	private CustomLabel label = new CustomLabel();
	private String id = "";
	private boolean value = true;
	private boolean disabled;
	private MaterialLabel lblError = new MaterialLabel();
	/**
	 * Creates a switch element
	 */
	public MaterialSwitch() {
		setElement(Document.get().createDivElement());
		setStyleName("switch");
		id = DOM.createUniqueId();
		input.getElement().setId(id);
		span.setStyleName("lever");
		input.setType("checkbox");
		label.add(input);
		label.add(span);
		add(label);
		add(lblError);
		lblError.getElement().getStyle().setMarginTop(16, Unit.PX);
	}
	
	/**
	 * Creates a material switch with default value
	 * @param value
	 */
	public MaterialSwitch(boolean value){
		this();
		setValue(value);
	}
	
	@Override
	protected void onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
		addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				event.preventDefault();
				//MaterialToast.alert(trigger + "");
				setValue(value);
			}
		});
	}

	public boolean isDisabled(){
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
		span.addStyleName("disabled");
		input.getElement().setAttribute("disabled", "true");
	}

	/**
	 * Set the value of switch component
	 * @param value
	 */
	public void setValue(boolean value) {
		if(value){
			input.getElement().setAttribute("checked", ""+value);
			this.value = false;
		}else{
			input.getElement().removeAttribute("checked");
			this.value = true;
		}
	}
	
	/**
	 * Gets the value of switch component
	 * @return
	 */
	public boolean getValue(){
		return value;
	}

	
	/**
	 * @return the input
	 */
	public CustomInput getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(CustomInput input) {
		this.input = input;
	}

	/**
	 * @return the span
	 */
	public CustomSpan getSpan() {
		return span;
	}

	/**
	 * @param span the span to set
	 */
	public void setSpan(CustomSpan span) {
		this.span = span;
	}

	/**
	 * @return the label
	 */
	public CustomLabel getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(CustomLabel label) {
		this.label = label;
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		if(isDisabled()){
			return null;
		}
		return addDomHandler(handler, ClickEvent.getType());
	}

	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);
	}

	@Override
	public void setOffset(String offset) {
		String tobeadded = "";
		String[] vals = offset.split(" ");
		for(String val : vals){
			tobeadded = tobeadded + " offset-" +  val;
		}
		this.addStyleName(tobeadded);
	}

	@Override
	public void setError(String error) {
		lblError.setText(error);
		lblError.addStyleName("field-error-label");
		lblError.removeStyleName("field-success-label");
		lblError.setVisible(true);
	}

	@Override
	public void setSuccess(String success) {
		lblError.setText(success);
		lblError.addStyleName("field-success-label");
		lblError.removeStyleName("field-error-label");
		lblError.setVisible(true);
	}
	
}
