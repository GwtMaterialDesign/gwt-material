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

import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasGrid;
import gwt.material.design.client.custom.HasSeparator;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.user.client.ui.Label;

//@formatter:off
/**
* 
* <p>Material Label will extend to GWT Label functionality with other material specifications
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
	<m:MaterialLabel text="I love material design" />
}
* </pre>
* </p>
* 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#buttons">Material Link</a>
*/
//@formatter:on
public class MaterialLabel extends Label implements HasGrid, HasSeparator, HasColors {
	
	String fontSize = "";
	String textColor = "";

	public MaterialLabel() {
	}

	public MaterialLabel(Element element) {
		super(element);
	}

	public MaterialLabel(String text, boolean wordWrap) {
		super(text, wordWrap);
	}

	public MaterialLabel(String text, Direction dir) {
		super(text, dir);
	}

	public MaterialLabel(String text, DirectionEstimator directionEstimator) {
		super(text, directionEstimator);
	}

	public MaterialLabel(String text) {
		super(text);
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
		this.getElement().getStyle().setFontSize(Double.valueOf(fontSize), Unit.EM);
	}

	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);
	}

	@Override
	public void setSeparator(boolean separator) {
		if (separator) {
			this.getElement().getStyle().setProperty("borderBottom", "1px solid #e9e9e9");
		}
	}
	
	@Override
	public void setOffset(String offset) {
		String cssName = "";
		for(String val : offset.split(" ")){
			cssName = cssName + " offset-" +  val;
		}
		this.addStyleName(cssName);
	}

	@Override
	public void setBackgroundColor(String bgColor) {
		addStyleName(bgColor);
	}

	@Override
	public void setTextColor(String textColor) {
		addStyleName(textColor + "-text");
	}
}
