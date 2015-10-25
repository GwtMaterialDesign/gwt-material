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

import gwt.material.design.client.custom.HasWaves;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

//@formatter:off
/**
* Card Element for card image. 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#cards">Material Cards</a>
*/

//@formatter:on
public class MaterialCardImage extends ComplexPanel implements HasWaves{

	public MaterialCardImage(){
		setElement(Document.get().createDivElement());
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public void add(final Widget child) {
    	if(child instanceof MaterialImage){
    		child.addStyleName("activator");
    	}
        add(child, (Element) getElement());
    }

    /**
     * Inserts a widget at a specific index
     *
     * @param child       - widget to be inserted
     * @param beforeIndex - index for the widget
     */
    public void insert(final Widget child, final int beforeIndex) {
        insert(child, (Element) getElement(), beforeIndex, true);
    }
	
	@Override
	public void setWaves(String waves) {
		addStyleName("waves-effect waves-" + waves);
		initWaves();
	}
	
	@Override
	public native void initWaves()/*-{
	    $wnd.Waves.displayEffect();
	}-*/;
	
	
}