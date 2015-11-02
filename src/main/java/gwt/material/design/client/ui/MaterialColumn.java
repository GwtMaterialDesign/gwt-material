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
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasGrid;
import gwt.material.design.client.custom.HasShadow;
import gwt.material.design.client.custom.HasWaves;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HasVisibility;
import gwt.material.design.client.custom.Waves;
import gwt.material.design.client.custom.mixin.WavesMixin;
//@formatter:off
/**
* MaterialColumn is panel that act as a fluid panel to easily sets your desired column.We are using 12 grid layout with screens small, medium and large.
* Just set grid='s12 m12 l12' to define your grid layout values.
* 
* <p>
* <h4>UiBinder Usage:</h4>
* <pre>
* {@code
* <m:MaterialColumn grid='s12 m6 l4'/>
* 	Small Device - 12 grid
* 	Medium Device - 6 grid
* 	Large Device - 4 grid
* }
* </pre>
* 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#grid">Material Column</a>
*/
//@formatter:on
public class MaterialColumn extends ComplexWidget implements HasWaves, HasVisibility {

	private final WavesMixin<MaterialColumn> wavesMixin = new WavesMixin<>(this);

	public MaterialColumn() {
		super(Document.get().createDivElement());
		setStyleName("col");
	}

	public MaterialColumn(int small, int medium, int large) {
		setStyleName("col");
		this.addStyleName("s"+small+" m"+medium + " l" + large);
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
