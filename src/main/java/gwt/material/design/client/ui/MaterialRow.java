package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasShadow;
import gwt.material.design.client.custom.HasWaves;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HasVisibility;

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

//@formatter:off
/**
* Material Row is a container (needed to define) for every Component that implements HasGrid functionality.
* 
* <p>
* <h3>UiBinder Usage:</h3>
* <pre>
* {@code 
* <m:MaterialRow>
* 	<m:MaterialColumn grid='s12 m6 l6'/>
* 	<m:MaterialColumn grid='s12 m6 l6'/>
* </m:MaterialRow>
* 
* }
* </pre>
* </p>
* 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#grid">Material Column</a>
*/
//@formatter:on
public class MaterialRow extends ComplexWidget implements HasColors, HasWaves, HasShadow, HasVisibility{

	public MaterialRow(){
		setElement(Document.get().createDivElement());
		setStyleName("row");
	}

	@Override
	public void setBackgroundColor(String bgColor) {
		addStyleName(bgColor);
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		initWaves();
	}

	@Override
	public void setTextColor(String textColor) {
		addStyleName(textColor + "-text");
	}

	@Override
	public void setWaves(String waves) {
		addStyleName("waves-effect waves-" + waves);
	}

	@Override
	public native void initWaves()/*-{
	    $wnd.Waves.displayEffect();
	}-*/;

	@Override
	public void setShadow(int shadow) {
		this.addStyleName("z-depth-" + shadow);
	}
	
	/**
	 * Sets the name of your scrollspy
	 * @param scrollspy
	 */
	public void setScrollspy(String scrollspy){
		this.addStyleName("scrollspy section");
		this.getElement().setId(scrollspy);
	}
	
	/**
	 * Sets the opacity of the panel
	 * @param opacity
	 */
	public void setOpacity(int opacity){
		this.getElement().getStyle().setOpacity(opacity);
	}
	
	/**
	 * Sets the padding of the panel
	 * @param padding
	 */
	public void setPadding(String padding){
		this.getElement().getStyle().setPadding(Double.parseDouble(padding), Unit.PCT);
	}
	
	public void setAlign(String align){
		addStyleName("align-" + align);
	}

}
