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

import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.HasWaves;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.custom.Waves;
import gwt.material.design.client.custom.mixin.WavesMixin;

//@formatter:off
/**
* Card Element for card image. 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#cards">Material Cards</a>
*/
//@formatter:on
public class MaterialCardImage extends ComplexWidget implements HasWaves {

	private final WavesMixin<MaterialCardImage> wavesMixin = new WavesMixin<>(this);

	public MaterialCardImage(){
		super(Document.get().createDivElement());
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public void add(final Widget child) {
    	if(child instanceof MaterialImage) {
    		child.addStyleName("activator");
    	}
        add(child, (Element) getElement());
    }

	@Override
	public void setWaves(WavesType waves) {
		wavesMixin.setWaves(waves);
	}

	@Override
	public WavesType getWaves() {
		return wavesMixin.getWaves();
	}
}