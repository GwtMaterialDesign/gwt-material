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
import gwt.material.design.client.custom.CustomParagraph;
import gwt.material.design.client.custom.HasError;
import gwt.material.design.client.custom.HasGrid;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerRegistration;

//@formatter:off
/**
* Material Range - a slider that initialize the minimum and maximum values. 
* 
* <p>
* <h3>UiBinder Usage:</h3>
* <pre>
* {@code 
* 
* <m:MaterialRange value="2" min="20" max="50" value="25"/>
* 
* }
* </pre>
* </p>
* 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material Range</a>
*/
//@formatter:on
public class MaterialRange extends ComplexWidget implements HasChangeHandlers, HasGrid, HasError {

	private CustomParagraph paragraph = new CustomParagraph();
	private CustomInput input = new CustomInput();
	
    private static String VALUE = "value";
    private static String MAX = "max";
    private static String MIN = "min";
    private static String INPUT = "INPUT";
    private MaterialLabel lblError = new MaterialLabel();
	
	// cache the embedded range input element
	private Element rangeElement = null;

	/**
	 * Creates a range
	 */
	public MaterialRange() {
		setElement(Document.get().createElement("form"));
		lblError.setVisible(false);
		paragraph.setStyleName("range-field");
		input.setType("range");
		paragraph.add(input);
		add(paragraph);
		add(lblError);
		lblError.getElement().getStyle().setMarginTop(-10, Unit.PX);
	}
	
	/**
	 * Creates a range with specified values
	 * @param min - start min value
	 * @param max - end max value
	 * @param value - default range value
	 */
	public MaterialRange(Integer min, Integer max, Integer value) {
		this();
		setMin(min);
		setMax(max);
		setValue(value);
	}

	/**
	 * Try to identify the embedded range elements input field (see ui xml)
	 * @return The found element or null if none found.
	 */
	private Element getRangeElement() {
		if (rangeElement == null) {
			NodeList<Element> elements = this.getElement().getElementsByTagName(INPUT);
			if (elements != null && elements.getLength() > 0) {
				rangeElement = elements.getItem(0);
			}
		}
		return rangeElement;
	}

	/**
	 * Retrieve the Integer value from the given Attribute of the range element
	 * @param attribute The name of the attribute on the range element
	 * @return The Integer vaulue read from the given attribute or null
	 */
	private Integer getIntFromRangeElement(String attribute) {
		Element ele = getRangeElement();
		if(ele != null) {
          return ele.getPropertyInt(attribute);
		}
		return null;
	}

	/**
	 * Set the given Integer value to the attribute of the range element.
	 */
	private void setIntToRangeElement(String attribute,Integer val) {
		Element ele = getRangeElement();
		if(ele != null) {
        	ele.setPropertyInt(attribute,val);
		}
	}
	
	/**
	 * Read the current value
	 * @return The Integer value or null
	 */
	public Integer getValue() {
		return getIntFromRangeElement(VALUE);
	}

	/**
	 * Write the current value
	 * @param value value must be &gt;= min and &lt;= max 
	 */
	public void setValue(Integer value) {
		if (value == null)return;
		if (value < getMin())return;
		if (value > getMax())return;
        setIntToRangeElement(VALUE,value);
	}

	/**
	 * Read the min value
	 * @return The Integer or null
	 */
	public Integer getMin() {
		return getIntFromRangeElement(MIN);
	}

	/**
	 * Write the current min value
	 * @param min value must be &lt; max 
	 */
	public void setMin(Integer min) {
        setIntToRangeElement(MIN,min);
	}

	/**
	 * Read the max value
	 * @return The Integer or null
	 */
	public Integer getMax() {
		return getIntFromRangeElement(MAX);
	}

	/**
	 * Write the current max value
	 * @param max value must be &gt; min
	 */
	public void setMax(Integer max) {
        setIntToRangeElement(MAX,max);
	}

	/**
	 * Register the ChangeHandler to become notified if the user changes the slider. 
	 * The Handler is called when the user releases the mouse only at the end of the slide
	 * operation.
	 */
	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(handler, ChangeEvent.getType());
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

	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);
	}
	
	@Override
	public void setOffset(String offset) {
		String cssName = "";
		for(String val : offset.split(" ")){
			cssName = cssName + " offset-" +  val;
		}
		this.addStyleName(cssName);
	}
}
